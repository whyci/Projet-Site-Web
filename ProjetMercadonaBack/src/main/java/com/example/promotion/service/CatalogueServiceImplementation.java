/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import com.example.promotion.repertoire.ProduitRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation des fonctions de CatalogueService.
 */
@Service
public class CatalogueServiceImplementation implements CatalogueService {

    @Autowired
    private ProduitRepertoire produitRepertoire;

    @Override
    public List<Produit> afficherCatalogueComplet() {
        return produitRepertoire.findAll();
    }
}
