package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProduitService {

    void enregistrerProduit(Produit produit);

    List<Produit> afficherProduits();

    void supprimerProduit(Long id);
}
