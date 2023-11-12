package com.example.promotion.modele;

import java.util.Date;

/**
 * Crée une promotion qui est utilisée pour les tests des contrôleurs.
 */
public class CreationPromotion {

    /**
     * Crée une instance de Promotion avec tous les attributs. Utilisée pour les tests de PromotionControleur.
     * @param id Identifiant de la promotion
     * @param debut Date de début de la promotion
     * @param fin Date de fin de la promotion
     * @param pourcentageRemise Pourcentage de remise de la promotion
     * @param idProduit Identifiant du produit associé à la promotion
     * @return Instance de la promotion créée avec les attributs données en paramètre
     */
    public static Promotion creationPromotion(Long id, Date debut, Date fin, Float pourcentageRemise, Long idProduit) {

        Promotion promotion = new Promotion();
        promotion.setId(id);
        promotion.setDebut(debut);
        promotion.setFin(fin);
        promotion.setPourcentageRemise(pourcentageRemise);
        promotion.setProduitIdCle(idProduit);

        return promotion;
    }
}
