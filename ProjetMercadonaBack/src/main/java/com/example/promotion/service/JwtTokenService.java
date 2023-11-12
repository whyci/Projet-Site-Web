package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;

/**
 * Service de JwtToken, génère et valide les token.
 */
public interface JwtTokenService {

    /**
     * Génère un token à la connexion d'un administrateur qui est utilisé pour accéder aux ressources de l'espace admin.
     * Les informations de l'administrateur sont utilisées pour la création du token.
     * @param administrateur Administrateur qui se connecte.
     * @return Token créé.
     */
    String genererToken(Administrateur administrateur);

    /**
     * Vérifie la validité du token renseigné pour l'accès aux ressources de l'espace admin.
     * @param token Token que l'on souhaite vérifier.
     * @return String : OK si le token est valide, KO si le token n'est pas valide.
     */
    String validerToken(String token);
}
