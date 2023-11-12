package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controleur du catalogue qui réceptionne les requêtes, les traite et renvoie les réponses au front.
 * Responsabilité principale et unique : Transmettre tous les produits au front.
 */
@Controller
@RequestMapping("/catalogue")
public class CatalogueControleur {

    /**
     * Instance de CatalogueService, interface d'accès à la base de donnée concernant la récupération de produit.
     */
    private final CatalogueService catalogueService;

    /**
     * Constructeur de la classe pour initialiser les instances de services qu'il utilise. Nécessaire pour les tests,
     * instancier les services mockés.
     * @param catalogueService Service du catalogue.
     */
    @Autowired
    public CatalogueControleur(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * Récupère et renvoie tout le catalogue
     * @return Entité de réponse contenant la liste des produits
     */
    @GetMapping("/complet")
    public ResponseEntity<List<Produit>> afficherComplet() {
        // Récupère le catalogue de la base de donnée en passant par catalogueService
        List<Produit> produits = catalogueService.afficherCatalogueComplet();

        // Renvoie le catalogue dans une entité de réponse au front
        return ResponseEntity.ok(produits);
    }
}
