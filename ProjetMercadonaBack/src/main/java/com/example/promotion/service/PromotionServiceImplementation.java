package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import com.example.promotion.modele.Promotion;
import com.example.promotion.repertoire.ProduitRepertoire;
import com.example.promotion.repertoire.PromotionRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImplementation implements PromotionService {

    @Autowired
    private ProduitRepertoire produitRepertoire;

    @Autowired
    private PromotionRepertoire promotionRepertoire;

    /**
     * Ajoute une nouvelle promotion et associe la promotion avec le produit correspondant à l'id donné.
     * @param promotion que l'on souhaite ajouter.
     * @param id du produit dont on souhaite associer une promotion.
     */
    @Override
    public void ajouterPromotion(Promotion promotion, Long id) {
        // Ajoute la promotion à la base de donnée.
        promotionRepertoire.save(promotion);

        // Vérifie si le produit existe dans la base de donnée.
        if (produitRepertoire.findById(Math.toIntExact(id)).isPresent()) {
            // Récupère le produit à changer.
            Produit produitAChanger = produitRepertoire.findById(Math.toIntExact(id)).get();
            // Associe la promotion au produit choisi.
            produitAChanger.setPromotionIdCle(promotion.getId());
            // Enregistre le changement dans la base de donnée.
            produitRepertoire.save(produitAChanger);
        }
    }

    @Override
    public List<Promotion> afficherPromotions() {
        return promotionRepertoire.findAll();
    }

    @Override
    public void supprimerPromotion(Long id) {
        promotionRepertoire.deleteById(Math.toIntExact(id));
    }
}
