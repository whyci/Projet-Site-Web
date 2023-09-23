package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promotion")
@CrossOrigin
public class PromotionControleur {
    @Autowired
    private ProduitService produitService;

    @PostMapping("/enregistrer")
    public String enregistrer(@RequestBody Produit produit) {
        produitService.enregistrerProduit(produit);
        return "Nouveau produit ajoute";
    }

}