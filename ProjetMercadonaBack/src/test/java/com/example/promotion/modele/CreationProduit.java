/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.modele;

/**
 * Crée un produit qui est utilisé pour les tests des contrôleurs.
 */
public class CreationProduit {

    /**
     * Méthode de création des produits utilisées pour les tests, utilisant toutes les propriétés d'un Produit
     * @param id Identifiant du produit
     * @param libelle Nom du produit
     * @param prix Prix du produit
     * @param categorie Categorie du produit
     * @param description Description du produit
     * @param image Image du produit (tableau de byte)
     * @param promotionIdCle Identifiant de la promotion associée
     * @return Le produit créé avec les paramètres
     */
    public static Produit creationTousDTProduit(Long id, String libelle, Float prix, String categorie, String description,
                                                byte[] image, Long promotionIdCle) {

        Produit produit = new Produit();
        produit.setId(id);
        produit.setLibelle(libelle);
        produit.setPrix(prix);
        produit.setCategorie(categorie);
        produit.setDescription(description);
        produit.setImage(image);
        Promotion promotion = new Promotion();
        promotion.setId(promotionIdCle);
        produit.setPromotion(promotion);

        return produit;
    }

    /**
     * Méthode de création des produits utilisées pour les tests, n'utilisant pas toutes les propriétés d'un Produit
     * @param libelle Nom du produit
     * @param prix Prix du produit
     * @param categorie Categorie du produit
     * @param description Description du produit
     * @return Le produit créé avec les paramètres
     */
    public static Produit creationPartieDTProduit(String libelle, Float prix, String categorie,
                                                  String description) {

        Produit produit = new Produit();
        produit.setLibelle(libelle);
        produit.setPrix(prix);
        produit.setCategorie(categorie);
        produit.setDescription(description);

        return produit;
    }
}
