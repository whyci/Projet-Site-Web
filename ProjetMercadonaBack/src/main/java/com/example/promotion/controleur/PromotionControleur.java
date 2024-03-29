/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */

package com.example.promotion.controleur;

import com.example.promotion.modele.Promotion;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur de Promotion qui réceptionne les requêtes, les traite et renvoie les réponses au front.
 * Responsabilités principales : Renvoyer toutes les promotions, ainsi que l'ajout de promotions.
 */
@Controller
@RequestMapping("/promotion")
public class PromotionControleur {

    /**
     * Instance de PromotionService, interface d'accès à la base de donnée concernant les promotions.
     */
    private final PromotionService promotionService;

    /**
     * Constructeur de la classe pour initialiser les instances de services qu'il utilise. Nécessaire pour les tests,
     * instancier les services mockés.
     * @param promotionService Service de promotion.
     */
    @Autowired
    public PromotionControleur(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    /**
     * Récupération de toutes les promotions pour les transmettre au front.
     * @return Entité de réponse qui comporte la liste des promotions.
     */
    @GetMapping("/complet")
    public ResponseEntity<List<Promotion>> recupererPromotions() {
        // Récupère la liste des promotions contenu dans la base de donnée.
        List<Promotion> promotions = promotionService.afficherPromotions();

        // Renvoie la liste de promotions.
        return ResponseEntity.ok(promotions);
    }

    /**
     * Ajoute une promotion a un produit identifié par un id.
     * @param promotion que l'on souhaite ajouter.
     * @param idProduit du produit associé à la promotion.
     * @return Chaine de caractère qui atteste de la requête concernée.
     */
    @PostMapping("/admin/ajouter/{id}")
    public ResponseEntity<ReponseString> ajouterPromotion(@RequestBody Promotion promotion,
                                                          @PathVariable("id") Long idProduit) {
        promotionService.ajouterPromotion(promotion, idProduit);

        String message = "Nouvelle promotion ajoutée du produit : "+idProduit.toString()+", avec une remise de : "
                +promotion.getPourcentageRemise()+"%";
        return ResponseEntity.ok(new ReponseString(message));
    }
}
