package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import com.example.promotion.repertoire.ProduitRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implémentation des fonctions de ProduitService.
 */
@Service
public class ProduitServiceImplementation implements ProduitService {

    @Autowired
    private ProduitRepertoire produitRepertoire;

    @Override
    public Long enregistrerProduitImage(Produit produit) {
        produitRepertoire.save(produit);
        return produit.getId();
    }

    @Override
    public void enregistrerProduitParametres(Produit produit, Long id) {

        // Vérifie si le produit existe dans la base de donnée. Si non, ne fait rien.
        if (produitRepertoire.findById(Math.toIntExact(id)).isPresent()) {

            // Récupère le produit à ajouter
            Produit produitAAjouter = produitRepertoire.findById(Math.toIntExact(id)).get();

            // Associe tous les paramètres du produit existant à celui qu'on souhaite ajouter.
            produitAAjouter.setLibelle(produit.getLibelle());
            produitAAjouter.setCategorie(produit.getCategorie());
            produitAAjouter.setDescription(produit.getDescription());
            produitAAjouter.setPrix(produit.getPrix());

            // Enregistre le changement dans la base de donnée.
            produitRepertoire.save(produitAAjouter);
        }
    }
}
