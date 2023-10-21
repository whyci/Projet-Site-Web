package com.example.promotion.reponse;

/* permet de définir les données comme immutable afin qu'elles ne puissent pas etre changées
   ces donnees seront renvoyées en réponse à la requete */
public record ReponseString(String message) {
}
