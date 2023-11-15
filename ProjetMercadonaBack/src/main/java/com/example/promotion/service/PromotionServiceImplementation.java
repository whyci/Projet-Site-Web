/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import com.example.promotion.modele.Promotion;
import com.example.promotion.repertoire.ProduitRepertoire;
import com.example.promotion.repertoire.PromotionRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation des fonctions de PromotionService.
 */
@Service
public class PromotionServiceImplementation implements PromotionService {

    @Autowired
    private ProduitRepertoire produitRepertoire;

    @Autowired
    private PromotionRepertoire promotionRepertoire;

    @Override
    public void ajouterPromotion(Promotion promotion, Long id) {
        // Ajoute la promotion à la base de donnée.
        promotionRepertoire.save(promotion);

        // Vérifie si le produit existe dans la base de donnée.
        if (produitRepertoire.findById(Math.toIntExact(id)).isPresent()) {
            // Récupère le produit à changer.
            Produit produitAChanger = produitRepertoire.findById(Math.toIntExact(id)).get();
            // Associe la promotion au produit choisi.
            produitAChanger.setPromotion(promotion);
            // Enregistre le changement dans la base de donnée.
            produitRepertoire.save(produitAChanger);
        }
    }

    @Override
    public List<Promotion> afficherPromotions() {
        return promotionRepertoire.findAll();
    }

}
