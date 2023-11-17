/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de Promotion. Lien avec la base de donnée pour y effectuer des opérations.
 */
@Service
public interface PromotionService {

    /**
     * Ajoute une nouvelle promotion et associe la promotion avec le produit correspondant à l'id donné.
     * @param promotion que l'on souhaite ajouter.
     * @param id du produit dont on souhaite associer une promotion.
     */
    void ajouterPromotion(Promotion promotion, Long id);

    /**
     * Retourne la liste de toutes les promotions.
     * @return Liste des promotions.
     */
    List<Promotion> afficherPromotions();

}
