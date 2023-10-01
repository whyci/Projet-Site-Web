package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProduitService {

    @Value("${upload.directory}")
    String uploadDirectory = null;

    Long enregistrerProduitImage(Produit produit);

    void enregistrerProduitParametres(Produit produit, Long id);

    List<Produit> afficherProduits();

    void supprimerProduit(Long id);
}
