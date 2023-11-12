package com.example.promotion.reponse;

/**
 * Permet de définir les données comme immutable afin qu'elles ne puissent pas etre changées
 * ces donnees seront renvoyées en réponse d'une requete
 * @param message Message que l'on envoi en réponse d'une requête.
 */
public record ReponseString(String message) {
}
