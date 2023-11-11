package com.example.promotion.controleur;

import com.example.promotion.modele.CreationPromotion;
import com.example.promotion.modele.Promotion;
import com.example.promotion.service.PromotionService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

/**
 * Tests de promotionControleur. Ils concernent l'ajout des produits en utilisant les deux étapes ; Envoi de l'image du
 * produit, puis envoi des informations du produit.
 * Note : Un contrôle des informations du produit est fait à la saisi de l'utilisateur. Il n'est pas nécessaire de
 * tester toutes les saisies possibles de l'utilisateur puisque cela est déjà géré en amont.
 */
public class TestPromotionControleur {

    /**
     * Donnée de test : Liste des promotions
     */
    private final List<Promotion> expListePromotion = new ArrayList<>();

    /**
     * Donnée de test : Promotion inexistante.
     */
    Promotion promoInexistant = CreationPromotion.creationPromotion(
            3L, Date.from(Instant.now()), Date.from(Instant.now().plus(Duration.ofDays(8))),
            0.85F, 8L
    );

    /**
     * Instance du mock de PromotionService pour intercepter les appels de fonctions.
     */
    @Mock
    private PromotionService promotionService;

    /**
     * Classe que l'on veut tester.
     */
    @InjectMocks
    @Resource
    private PromotionControleur promotionControleur;

    @BeforeEach
    public void prepTestPromotionControleur() {

        // Initialise le mock de promotionService
        MockitoAnnotations.initMocks(this);

        /* - - - - Données de test - - - - */
        /* Les produits ont des valeurs aléatoires à part la première (id du produit) */
        Promotion promo50 = CreationPromotion.creationPromotion(
                1L, Date.from(Instant.now()), Date.from(Instant.now().plus(Duration.ofDays(2))),
                0.5F, 25L
        );
        Promotion promo40 = CreationPromotion.creationPromotion(
                2L, Date.from(Instant.now()), Date.from(Instant.now().plus(Duration.ofDays(14))),
                0.4F, 12L
        );

        // Ajout des promotions dans la liste attendue
        expListePromotion.add(promo50);
        expListePromotion.add(promo40);

        /* - - - - Comportement du mock - - - - */

        // Remplacement du résultat de afficherPromotions en retournant la liste des promotions attendue
        when(promotionService.afficherPromotions())
                .thenReturn(expListePromotion);
    }

    /**
     * Test de récupération des promotions
     */
    @Test
    public void testRecupererPromotion() {

        /* - - - - Exécution des tests - - - - */
        promotionControleur = new PromotionControleur(promotionService);
        ResponseEntity<List<Promotion>> resReponseListePromotion = promotionControleur.recupererPromotions();

        /* - - - - Assertions des résultats de tests - - - - */

        // Vérification des appels du mock
        verify(promotionService).afficherPromotions();

        // Vérification pour chaque élément de la liste du résultat
        for (int indexPromotion = 0;
             indexPromotion < Objects.requireNonNull(resReponseListePromotion.getBody()).size();
             indexPromotion ++) {

            // Identifiant promotion
            assertEquals("[Echec -> Test Promotion Controleur] identifiant promotion",
                    expListePromotion.get(indexPromotion).getId(),
                    resReponseListePromotion.getBody().get(indexPromotion).getId());

            // Pourcentage de remise
            assertEquals("[Echec -> Test Promotion Controleur] pourcentage de remise",
                    expListePromotion.get(indexPromotion).getPourcentageRemise(),
                    resReponseListePromotion.getBody().get(indexPromotion).getPourcentageRemise());

            // Date de début
            assertEquals("[Echec -> Test Promotion Controleur] date début",
                    expListePromotion.get(indexPromotion).getDebut(),
                    resReponseListePromotion.getBody().get(indexPromotion).getDebut());

            // Date de fin
            assertEquals("[Echec -> Test Promotion Controleur] date fin",
                    expListePromotion.get(indexPromotion).getFin(),
                    resReponseListePromotion.getBody().get(indexPromotion).getFin());

            // Identifiant du produit associé
            assertEquals("[Echec -> Test Promotion Controleur] id produit associé",
                    expListePromotion.get(indexPromotion).getProduitIdCle(),
                    resReponseListePromotion.getBody().get(indexPromotion).getProduitIdCle());

            // Comparaison identifiant avec produit inexistant
            assertNotEquals("[Echec -> Test Promotion Controleur] identifiant promotion inexistant",
                    promoInexistant.getId(),
                    resReponseListePromotion.getBody().get(indexPromotion).getId());
        }

        // Vérification pour chaque élément de la liste de expected
        for (int indexPromotion = 0;
             indexPromotion < expListePromotion.size();
             indexPromotion ++) {

            // Identifiant promotion
            assertEquals("[Echec -> Test Promotion Controleur] identifiant promotion",
                    expListePromotion.get(indexPromotion).getId(),
                    resReponseListePromotion.getBody().get(indexPromotion).getId());

            // Pourcentage de remise
            assertEquals("[Echec -> Test Promotion Controleur] pourcentage de remise",
                    expListePromotion.get(indexPromotion).getPourcentageRemise(),
                    resReponseListePromotion.getBody().get(indexPromotion).getPourcentageRemise());

            // Date de début
            assertEquals("[Echec -> Test Promotion Controleur] date début",
                    expListePromotion.get(indexPromotion).getDebut(),
                    resReponseListePromotion.getBody().get(indexPromotion).getDebut());

            // Date de fin
            assertEquals("[Echec -> Test Promotion Controleur] date fin",
                    expListePromotion.get(indexPromotion).getFin(),
                    resReponseListePromotion.getBody().get(indexPromotion).getFin());

            // Identifiant du produit associé
            assertEquals("[Echec -> Test Promotion Controleur] id produit associé",
                    expListePromotion.get(indexPromotion).getProduitIdCle(),
                    resReponseListePromotion.getBody().get(indexPromotion).getProduitIdCle());

            // Comparaison identifiant avec produit inexistant
            assertNotEquals("[Echec -> Test Promotion Controleur] identifiant promotion inexistant",
                    promoInexistant.getId(),
                    resReponseListePromotion.getBody().get(indexPromotion).getId());
        }
    }

    /**
     * Test d'ajout d'une promotion.
     */
    @Test
    public void testAjoutPromotion() {

        /* - - - - Données de test - - - - */
        Long idProduit = 5L;
        Promotion promo50 = CreationPromotion.creationPromotion(
                1L, Date.from(Instant.now()), Date.from(Instant.now().plus(Duration.ofDays(2))),
                0.5F, idProduit
        );

        /* - - - - Exécution des tests - - - - */
        promotionControleur = new PromotionControleur(promotionService);
        promotionControleur.ajouterPromotion(promo50, idProduit);

        /* - - - - Assertions des résultats de tests - - - - */
        // Vérification des appels du mock
        verify(promotionService).ajouterPromotion(promo50, idProduit);
    }
}
