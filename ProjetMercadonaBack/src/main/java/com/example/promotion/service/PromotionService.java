package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import com.example.promotion.modele.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionService {

    void ajouterPromotion(Promotion promotion, Long id);

    void supprimerPromotion(Long id);
}
