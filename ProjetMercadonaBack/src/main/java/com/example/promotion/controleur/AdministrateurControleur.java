/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.controleur;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.reponse.ReponseAdministrateur;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.AdministrateurService;
import com.example.promotion.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * Instance de JwtTokenService, pour la gestion de token.
     */
    private final JwtTokenService jwtTokenService;

    /**
     * Constructeur de la classe pour initialiser les instances de services qu'il utilise. Nécessaire pour les tests,
     * instancier les services mockés.
     * @param administrateurService Service de adminitrateur.
     * @param jwtTokenService Service de JwToken.
     */
    @Autowired
    public AdministrateurControleur(AdministrateurService administrateurService, JwtTokenService jwtTokenService) {
        this.administrateurService = administrateurService;
        this.jwtTokenService = jwtTokenService;
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
        List<Administrateur> administrateur = administrateurService.connecterAdministrateur(adresseMail.toLowerCase(), motDePasse);

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
     * Inscription d'un administrateur et enregistrement dans la base de donnée. Il est possible ici d'ajouter un code
     * administrateur à condition de modifier la base de donnée. Il servira à vérifier que l'inscription est valable.
     * @param administrateur Informations concernants l'administrateur.
     * @return Réponse relative à la réussite de l'inscription.
     */
    @PostMapping("/inscrire")
    public ResponseEntity<ReponseString> inscrireAdministrateur(@RequestBody Administrateur administrateur) {
        administrateurService.inscrireAdministrateur(administrateur);
        String message = "Création administrateur : " + administrateur.getPrenom() + " " + administrateur.getNom() + " terminé avec mail : " +
                administrateur.getAdresseMail() + ", mdp : " + administrateur.getMotDePasse() + " !";
        return ResponseEntity.ok(new ReponseString(message));
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
