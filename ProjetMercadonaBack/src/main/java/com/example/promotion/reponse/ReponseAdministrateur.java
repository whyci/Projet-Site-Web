/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.reponse;

/**
 * Permet de définir les données comme immutable afin qu'elles ne puissent pas etre changées
 * ces donnees seront renvoyées en réponse d'une requête.
 * @param token Token que l'on envoi en réponse d'une réquête.
 */
public record ReponseAdministrateur(String token) {
}
