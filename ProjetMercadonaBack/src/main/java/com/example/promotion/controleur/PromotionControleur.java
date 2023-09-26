package com.example.promotion.controleur;

import com.example.promotion.modele.Promotion;
import com.example.promotion.response.StringResponse;
import com.example.promotion.service.ProduitService;
import com.example.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/promotion")
@CrossOrigin
public class PromotionControleur {
    @Autowired
    private PromotionService promotionService;

    /**
     * Ajoute une promotion a un produit.
     * @param promotion que l'on souhaite ajouter.
     * @param id du produit associé à la promotion.
     * @return Chaine de caractère qui atteste de la requête concernée.
     */
    @PostMapping("/ajouter/{id}")
    public ResponseEntity<StringResponse> ajouterPromotion(@RequestBody Promotion promotion, @PathVariable("id") Long id) {
        promotionService.ajouterPromotion(promotion, id);

        String message = "Nouvelle promotion ajoutée du produit : "+id.toString()+", avec une remise de : "
                +promotion.getPourcentageRemise()+"%";
        return ResponseEntity.ok(new StringResponse(message));
    }

    /**
     * Supprime une promotion.
     * @param id de la promotion que l'on souhaite supprimer.
     * @return Chaine de caractère qui atteste de la requête concernée.
     */
    @PostMapping("/supprimer/{id}")
    public ResponseEntity<StringResponse> supprimerPromotion(@PathVariable("id") Long id) {
        promotionService.supprimerPromotion(id);

        String message = "Promotion supprimée à l'id : "+id;
        return ResponseEntity.ok(new StringResponse(message));
    }

}
