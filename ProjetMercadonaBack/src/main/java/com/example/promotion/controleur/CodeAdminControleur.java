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
@RequestMapping("/codeadmin")
public class CodeAdminControleur {

    /**
     * Instance de CodeAdminService, interface d'accès à la base de donnée concernant les codeAdmin.
     */
    private final CodeAdminService codeAdminService;

    /**
     * Constructeur de la classe pour initialiser les instances de services qu'il utilise. Nécessaire pour les tests,
     * instancier les services mockés.
     * @param codeAdminService Service de CodeAdmin.
     */
    @Autowired
    public CodeAdminControleur(CodeAdminService codeAdminService) {
        this.codeAdminService = codeAdminService;
    }

    /**
     * Ajout d'un codeAdmin dans la base de donnée.
     * @param code Code admin utilisé pour la création de l'administrateur.
     * @return Réponse relative à la réussite de l'ajout du codeAdmin :
     * - Erreur pour aucun code reçu dans la requête : KO_code_vide
     * - Erreur pour un code déjà existant dans la base de donnée : KO_code_existant
     * - Réussite : OK
     */
    @PostMapping("/ajouter/{code}")
    public ResponseEntity<ReponseString> ajouterCodeAdmin(@PathVariable("code") String code) {

        // S'il n'y a pas de code dans la requête, on retourne KO_code_vide
        if (Objects.equals(code, "")) {
            return ResponseEntity.ok(new ReponseString("KO_code_vide"));
        }

        // Récupère le codeAdmin donné
        List<CodeAdmin> listeCodeAdmin = codeAdminService.verifierDisponibitilite(code);

        // Si le codeAdmin existe déjà, retourne : KO_code_existant
        if (!listeCodeAdmin.isEmpty()) {
            return ResponseEntity.ok(new ReponseString("KO_code_existant"));
        }

        // On ajoute le codeAdmin dans la base de donnée
        codeAdminService.ajoutCodeAdmin(code);

        // Ajout terminé, on retourne OK
        return ResponseEntity.ok(new ReponseString("OK"));
    }

}
