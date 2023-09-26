package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.response.StringResponse;
import com.example.promotion.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produit")
@CrossOrigin(origins = "*")
public class ProduitControleur {
    @Autowired
    private ProduitService produitService;

    @PostMapping("/ajouter")
    public ResponseEntity<StringResponse> ajouterProduit(@RequestBody Produit produit) {
        produitService.enregistrerProduit(produit);
        String message = "Nouveau produit ajoute : " + produit.getLibelle() + ", de categorie : " + produit.getCategorie()
                + ", avec id : " + produit.getId();
        return ResponseEntity.ok(new StringResponse(message));
    }

    @GetMapping("/affichage")
    public List<Produit> afficherProduits() {
        return produitService.afficherProduits();
    }

    @DeleteMapping("/supprimer/{id}")
    public String supprimerProduit(@PathVariable("id") Long id) {
        produitService.supprimerProduit(id);
        return "Suppression produit avec id : " + id.toString();
    }

}