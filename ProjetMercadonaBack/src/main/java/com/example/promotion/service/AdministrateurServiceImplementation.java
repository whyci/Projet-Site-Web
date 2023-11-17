/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.repertoire.AdministrateurRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation des fonctions de AdministrateurService.
 */
@Service
public class AdministrateurServiceImplementation implements AdministrateurService {

    @Autowired
    private AdministrateurRepertoire administrateurRepertoire;

    @Override
    public List<Administrateur> connecterAdministrateur(String adresseMail, String motDePasse) {
        return administrateurRepertoire.findAdministrateursByAdresseMailAndMotDePasse(adresseMail, motDePasse);
    }

    @Override
    public void inscrireAdministrateur(Administrateur administrateur) {
        administrateurRepertoire.save(administrateur);
    }
}
