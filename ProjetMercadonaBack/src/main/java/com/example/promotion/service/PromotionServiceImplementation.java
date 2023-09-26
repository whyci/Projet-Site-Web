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

    @Override
    public void ajouterPromotion(Promotion promotion, Long id) {

    }

    @Override
    public void supprimerPromotion(Long id) {
        promotionRepertoire.deleteById(Math.toIntExact(id));
    }
}
