/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.controleur;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.CodeAdmin;
import com.example.promotion.reponse.ReponseAdministrateur;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.AdministrateurService;
import com.example.promotion.service.CodeAdminService;
import com.example.promotion.service.JwtTokenService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controleur de Administrateur qui réceptionne les requêtes, les traite et renvoie les réponses au front.
 * Responsabilités principales : Assure l'inscription et la connexion des administrateurs, ainsi que la vérification de
 * token pour l'accès aux ressources de l'espace admin.
 */
@Controller
@RequestMapping("/administrateur")
public class AdministrateurControleur {

    /**
     * Instance de AdministrateurService, interface d'accès à la base de donnée concernant les administrateurs.
     */
    private final AdministrateurService administrateurService;

    /**
     * Instance de CodeAdminService, interface d'accès à la base de donnée concernant les codeAdmin.
     */
    private final CodeAdminService codeAdminService;

    /**
     * Instance de JwtTokenService, pour la gestion de token.
     */
    private final JwtTokenService jwtTokenService;

    /**
     * Constructeur de la classe pour initialiser les instances de services qu'il utilise. Nécessaire pour les tests,
     * instancier les services mockés.
     * @param administrateurService Service de adminitrateur.
     * @param jwtTokenService Service de JwToken.
     * @param codeAdminService Service de CodeAdmin.
     */
    @Autowired
    public AdministrateurControleur(AdministrateurService administrateurService,
                                    JwtTokenService jwtTokenService,
                                    CodeAdminService codeAdminService) {
        this.administrateurService = administrateurService;
        this.jwtTokenService = jwtTokenService;
        this.codeAdminService = codeAdminService;
    }

    /**
     * Assure la connexion de l'administrateur en contrôlant ses informations (mail et mdp) et génère un token pour
     * l'accès aux ressources liées à l'espace admin.
     * @param adresseMail adresse mail pour se connecter
     * @param motDePasse mot de passe pour se connecter
     * @return token pour contrôler l'accès aux ressources liées à l'espace admin
     */
    @GetMapping("/connecter/{adresseMail}/{mdp}")
    public ResponseEntity<ReponseAdministrateur> connecterAdministrateur(@PathVariable("adresseMail") String adresseMail,
                                                                         @PathVariable("mdp") String motDePasse) {

        if (Objects.equals(adresseMail, "") || Objects.equals(motDePasse, ""))
            return ResponseEntity.ok(new ReponseAdministrateur("KO"));

        // Récupère le résultat de la recherche dans la base de donnée des identifiants donnés.
        List<Administrateur> administrateur = administrateurService.connecterAdministrateur(adresseMail, motDePasse);

        ReponseAdministrateur reponseAdministrateur;

        // Vérifie si un administrateur a été trouvé dans la base de donnée avec les bons identifiants.
        if (!administrateur.isEmpty()) {
            // Connexion valide
            reponseAdministrateur = new ReponseAdministrateur(jwtTokenService.genererToken(administrateur.get(0)));
        } else {
            // Connexion invalide
            System.out.println("Admin not found !");
            reponseAdministrateur = new ReponseAdministrateur("KO");
        }
        return ResponseEntity.ok(reponseAdministrateur);
    }

    /**
     * Inscription d'un administrateur et enregistrement dans la base de donnée. Un code est aussi donné en paramètre
     * pour assurer le droit de création d'un administrateur. Une vérification du codeAdmin est faite avant d'ajouter
     * l'administrateur et de l'associer au codeAdmin donné.
     * Amélioration possible : vérifier si un administrateur existe déjà avec ces identifiants.
     * @param administrateur Informations concernants l'administrateur.
     * @param code Code admin utilisé pour la création de l'administrateur.
     * @return Réponse relative à la réussite de l'inscription.
     * - Erreur pour aucun code reçu dans la requête : KO_code_vide
     * - Erreur pour un code qui n'existe pas dans la base de donnée : KO_code_incorrect
     * - Erreur pour un code qui n'est pas disponible (déjà associé à un administrateur) : KO_dispo_code
     * - Réussite : OK
     */
    @PostMapping("/inscrire/{code}")
    public ResponseEntity<ReponseString> inscrireAdministrateur(@RequestBody Administrateur administrateur,
                                                                @PathVariable("code") String code) {

        // S'il n'y a pas de code dans la requête, on retourne KO_code_vide
        if (Objects.equals(code, "")) {
            return ResponseEntity.ok(new ReponseString("KO_code_vide"));
        }

        // Récupère le codeAdmin donné
        List<CodeAdmin> listeCodeAdmin = codeAdminService.verifierDisponibitilite(code);

        // S'il n'existe pas, retourne : KO_code_incorrect
        if (listeCodeAdmin.isEmpty()) {
            return ResponseEntity.ok(new ReponseString("KO_code_incorrect"));
        }

        // Sinon, récupère le (seul) code admin dans la liste
        CodeAdmin codeAdmin = listeCodeAdmin.get(0);

        // Vérifie s'il est libre
        if (codeAdmin.getAdministrateur() != null) {
            // Pas libre, retourne : KO_dispo_code
            return ResponseEntity.ok(new ReponseString("KO_dispo_code"));
        }

        // Tout est ok, on inscrit l'administrateur
        administrateurService.inscrireAdministrateur(administrateur);

        // Associe l'administrateur au code
        codeAdminService.associationAdmin(codeAdmin.getId(), administrateur);

        // Création terminé, on retourne OK
        return ResponseEntity.ok(new ReponseString("OK"));
    }

    /**
     * Vérifie la validité d'un token envoyé par l'entête d'authorisation.
     * @param authorizationHeader L'entête d'authorisation qui contient le token à vérifier.
     * @return Réponse à envoyé au front. OK accès validé, KO accès non validé.
     */
    @GetMapping("/acces")
    public ResponseEntity<String> accesEspaceAdmin(@RequestHeader(name = "Authorization") String authorizationHeader) {

        // Si aucun token reçu, répond par un KO.
        if (authorizationHeader == null) { return ResponseEntity.ok("KO"); }

        // Si l'entête est différente de ce qui est attendu, répond par un KO.
        if (!authorizationHeader.startsWith("Bearer ")) { return ResponseEntity.ok("KO"); }

        // Vérifie la validité du token
        return ResponseEntity.ok(jwtTokenService.validerToken(authorizationHeader.substring(7)));
    }


}
