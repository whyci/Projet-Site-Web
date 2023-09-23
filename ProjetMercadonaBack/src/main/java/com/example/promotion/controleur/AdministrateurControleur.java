package com.example.promotion.controleur;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.Produit;
import com.example.promotion.service.AdministrateurService;
import com.example.promotion.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrateur")
@CrossOrigin(origins = "*")
public class AdministrateurControleur {
    @Autowired
    private AdministrateurService administrateurService;

    @PostMapping("/inscription")
    public String inscrire(@RequestBody Administrateur administrateur) {
        administrateurService.inscription(administrateur);
        return "Nouvel administrateur ajouté";
    }

}