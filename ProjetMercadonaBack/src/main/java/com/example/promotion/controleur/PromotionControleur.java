package com.example.promotion.controleur;

import com.example.promotion.modele.Promotion;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/promotion")
@CrossOrigin
public class PromotionControleur {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/complet")
    public ResponseEntity<List<Promotion>> recupererPromotions() {
        // Récupère la liste des promotions contenu dans la base de donnée.
        List<Promotion> promotions = promotionService.afficherPromotions();

        // Renvoie la liste de promotions.
        return ResponseEntity.ok(promotions);
    }

    /**
     * Ajoute une promotion a un produit.
     * @param promotion que l'on souhaite ajouter.
     * @param id du produit associé à la promotion.
     * @return Chaine de caractère qui atteste de la requête concernée.
     */
    @PostMapping("/admin/ajouter/{id}")
    public ResponseEntity<ReponseString> ajouterPromotion(@RequestBody Promotion promotion,
                                                          @PathVariable("id") Long id) {
        promotionService.ajouterPromotion(promotion, id);

        String message = "Nouvelle promotion ajoutée du produit : "+id.toString()+", avec une remise de : "
                +promotion.getPourcentageRemise()+"%";
        return ResponseEntity.ok(new ReponseString(message));
    }

    /**
     * Supprime une promotion.
     * @param id de la promotion que l'on souhaite supprimer.
     * @return Chaine de caractère qui atteste de la requête concernée.
     */
    @PostMapping("/admin/supprimer/{id}")
    public ResponseEntity<ReponseString> supprimerPromotion(@PathVariable("id") Long id) {
        promotionService.supprimerPromotion(id);

        String message = "Promotion supprimée à l'id : "+id;
        return ResponseEntity.ok(new ReponseString(message));
    }

}
