package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.response.ProduitResponse;
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
        // Your logic to retrieve a List of Produit objects
        List<Produit> produits = catalogueService.afficherCatalogueComplet();

        // Create a ResponseEntity and return the List of Produits
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/filtre/{id}")
    public ResponseEntity<Map<String, Produit>> afficherFiltre(@PathVariable Long id) {
        Optional<Produit> produitOptional = catalogueService.afficherCatalogueFiltre(id);
        if (produitOptional.isPresent()) {
            Produit produit = produitOptional.get();

            // Création d'une map pour stocker l'objet Produit
            Map<String, Produit> responseData = new HashMap<>();
            responseData.put("data", produit);

            return ResponseEntity.ok(responseData);
        } else {
            // Handle the case when the Produit is not found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filtre/categorie/{categorie}")
    public ResponseEntity<ProduitResponse> afficherFiltreCategorie(@PathVariable String categorie) {
        List<Produit> produitList = catalogueService.afficherCatalogueFiltreCategorie(categorie);
        ProduitResponse response = new ProduitResponse(produitList);

        return ResponseEntity.ok(response);
    }
}
