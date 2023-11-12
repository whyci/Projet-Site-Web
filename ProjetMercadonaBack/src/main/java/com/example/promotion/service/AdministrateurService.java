/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de Administrateur. Lien avec la base de donnée pour y effectuer des opérations.
 */
@Service
public interface AdministrateurService {

    /**
     * Pour la connexion d'un administrateur, vérifie qu'il existe bien dans la base de donnée par ses informations
     * d'authentification (adresse mail et mot de passe). Etape qui précéde la création de token.
     * @param adresseMail Adresse mail de l'administrateur.
     * @param motDePasse Mot de passe de l'administrateur.
     * @return Liste d'administrateur qui est le résultat de la recherche.
     */
    List<Administrateur> connecterAdministrateur(String adresseMail, String motDePasse);

    /**
     * Inscrit un administrateur dans la base de donnée.
     * @param administrateur Administrateur que l'on inscrit.
     */
    void inscrireAdministrateur(Administrateur administrateur);
}
