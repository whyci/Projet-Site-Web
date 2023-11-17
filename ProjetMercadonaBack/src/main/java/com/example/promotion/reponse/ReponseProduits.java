/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.reponse;

import com.example.promotion.modele.Produit;
import java.util.List;

/** SUPPRESSION !!!
 * Permet de définir les données comme immutable afin qu'elles ne puissent pas etre changées
 * ces donnees seront renvoyées en réponse de la requete.
 * @param produits Liste de produits que l'on
 */
public record ReponseProduits(List<Produit> produits) {
}
