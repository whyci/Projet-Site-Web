package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/catalogue")
@CrossOrigin(origins = "*")
public class CatalogueControleur {
    @Autowired
    private CatalogueService catalogueService;

    @GetMapping("/complet")
    public ResponseEntity<List<Produit>> afficherComplet() {
        List<Produit> produits = catalogueService.afficherCatalogueComplet();

        return ResponseEntity.ok(produits);
    }

    @GetMapping("/filtre/{id}")
    public ResponseEntity<Map<String, Produit>> afficherFiltre(@PathVariable Integer id) {
        Optional<Produit> produitOptional = catalogueService.afficherCatalogueFiltre(id);
        if (produitOptional.isPresent()) {
            Produit produit = produitOptional.get();

            // Création d'une map pour stocker l'objet Produit
            Map<String, Produit> responseData = new HashMap<>();
            responseData.put("data", produit);

            return ResponseEntity.ok(responseData);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
