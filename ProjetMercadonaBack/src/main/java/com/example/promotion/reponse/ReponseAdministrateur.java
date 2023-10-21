package com.example.promotion.reponse;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.Produit;

import java.util.List;

/* permet de définir les données comme immutable afin qu'elles ne puissent pas etre changées
   ces donnees seront renvoyées en réponse à la requete */
public record ReponseAdministrateur(String token) {
}
