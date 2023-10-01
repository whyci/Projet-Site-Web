package com.example.promotion.reponse;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.Produit;

import java.util.List;

public record ReponseAdministrateur(List<Administrateur> administrateurs, String token) {
}