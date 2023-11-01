package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.reponse.ReponseProduits;
import com.example.promotion.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/catalogue")
@CrossOrigin(origins = "*")
public class CatalogueControleur {

    private final CatalogueService catalogueService;

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
