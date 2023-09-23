package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void ajouterProduit(@RequestBody Produit produit) {
        produitService.enregistrerProduit(produit);
    }

    @GetMapping("/affichage")
    public List<Produit> afficherProduits() {
        return produitService.afficherProduits();
    }

    @DeleteMapping("/supprimer/{id}")
    public String supprimerProduit(@PathVariable("id") Integer id) {
        produitService.supprimerProduit(id);
        return "Suppression produit avec id : " + id.toString();
    }

}