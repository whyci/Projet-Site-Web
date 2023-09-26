package com.example.promotion.response;

import com.example.promotion.modele.Produit;

import java.util.List;

public class ProduitResponse {
    private final List<Produit> produits;

    public ProduitResponse(List<Produit> produits) {
        this.produits = produits;
    }

    public List<Produit> getProduits() {
        return produits;
    }
}