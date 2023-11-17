/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service de Produit. Lien avec la base de donnée pour y effectuer des opérations.
 */
@Service
public interface ProduitService {

    @Value("${upload.directory}")
    String uploadDirectory = null;

    /**
     * Ajout d'un produit contenant uniquement son image dans la base de donnée et retourne son identifiant créé.
     * Fait partie de la première étape (sur 2) d'ajout d'un produit.
     * @param produit Produit contenant son image.
     * @return Identifiant du produit.
     */
    Long enregistrerProduitImage(Produit produit);

    /**
     * Ajout des paramètres du produit précédemment et identifié par son identifiant. Paramètres = toutes informations
     * du produit (libellé, prix, descritpion, catégorie) à part son image.
     * Fait partie de la dernière étape d'ajout d'un produit.
     * @param produit Produit contenant les paramètres.
     * @param id Identifiant du produit.
     */
    void enregistrerProduitParametres(Produit produit, Long id);
}
