package com.example.promotion.controleur;

import com.example.promotion.modele.CreationProduit;
import com.example.promotion.modele.Produit;
import com.example.promotion.service.CatalogueService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;


/**
 * Tests de catalogueControleur. Ils concernent la récupération du catalogue
 */
public class TestCatalogueControleur {

    /**
     * Liste que l'on envoie à la fonction à tester, ainsi que celle que l'on s'attend à avoir après exécution du test.
     */
    private final List<Produit> expListeProduits = new ArrayList<>();


    /**
     * Instance du mock de CatalogueService pour intercepter les appels de fonctions.
     */
    @Mock
    private CatalogueService catalogueService;

    /**
     * Classe que l'on veut tester pour laquelle on souhaite injecter un mock de catalogueService
     */
    @InjectMocks
    @Resource
    private CatalogueControleur catalogueControleur;

    /**
     * Préparation des tests du catalogueControleur.
     * Série d'instructions que l'on exécute avant tous tests du catalogueControleur.
     */
    @BeforeEach
    public void prepTestCatalogueControleur() {

        // Initialise le mock de catalogueService
        MockitoAnnotations.initMocks(this);

        /* - - - - Données de test - - - - */

        /* - - - - Données de test - - - - */
        /* Les produits ont des valeurs aléatoires à part la première (id du produit) */
        Produit p1Salade = CreationProduit.creationTousDTProduit(
                1L, "Salade", 5.0F, "Légume", "Feuilles vertes",
                new byte[]{}, 0L
        );
        Produit p2Paprika = CreationProduit.creationTousDTProduit(
                2L, "Paprika", 2.0F, "Condiment", "Mercadona commercant",
                new byte[]{0x00}, 978L
        );
        Produit p3Coca = CreationProduit.creationTousDTProduit(
                3L, "Coca-cola", 11.5F, "Légume", "Bouteille 1.5L",
                new byte[]{0x44, 0x54, 0x41, 0x75}, 5L
        );

        // Ajout des produits dans la liste attendue
        expListeProduits.add(p1Salade);
        expListeProduits.add(p2Paprika);
        expListeProduits.add(p3Coca);

        /* - - - - Comportement du mock - - - - */

        // Remplace le comportement de la fonction mockée afficherCatalogueComplet pour renvoyer la liste attendue
        when(catalogueService.afficherCatalogueComplet()).thenReturn(expListeProduits);
    }

    /**
     * Test de la bonne récupération du catalogue
     */
    @Test
    public void testRecupererCatalogue() {

        /* - - - - Exécution des tests - - - - */
        catalogueControleur = new CatalogueControleur(catalogueService);
        ResponseEntity<List<Produit>> actReponseListeProduits = catalogueControleur.afficherComplet();

        /* - - - - Assertions des résultats de tests - - - - */

        // Vérification des appels du mock
        Mockito.verify(catalogueService).afficherCatalogueComplet();
        Mockito.verify(catalogueService, never()).afficherCatalogueFiltre(anyLong());

        // Vérification pour chaque élément de la liste de actual
        for (int indexProduitAct = 0;
             indexProduitAct < Objects.requireNonNull(actReponseListeProduits.getBody()).size();
             indexProduitAct ++) {

            // Libelle
            assertEquals("[Echec -> Test Catalogue Controleur] élément de actual",
                    expListeProduits.get(indexProduitAct).getLibelle(),
                    actReponseListeProduits.getBody().get(indexProduitAct).getLibelle());

            // Prix
            assertEquals("[Echec -> Test Catalogue Controleur] élément de actual",
                    expListeProduits.get(indexProduitAct).getPrix(),
                    actReponseListeProduits.getBody().get(indexProduitAct).getPrix());

            // Categorie
            assertEquals("[Echec -> Test Catalogue Controleur] élément de actual",
                    expListeProduits.get(indexProduitAct).getCategorie(),
                    actReponseListeProduits.getBody().get(indexProduitAct).getCategorie());

            // Description
            assertEquals("[Echec -> Test Catalogue Controleur] élément de actual",
                    expListeProduits.get(indexProduitAct).getDescription(),
                    actReponseListeProduits.getBody().get(indexProduitAct).getDescription());

            // Image
            assertEquals("[Echec -> Test Catalogue Controleur] élément de actual",
                    expListeProduits.get(indexProduitAct).getImage(),
                    actReponseListeProduits.getBody().get(indexProduitAct).getImage());

            // PromotionIdCle
            assertEquals("[Echec -> Test Catalogue Controleur] élément de actual",
                    expListeProduits.get(indexProduitAct).getPromotionIdCle(),
                    actReponseListeProduits.getBody().get(indexProduitAct).getPromotionIdCle());
        }

        // Vérification pour chaque élément de la liste de expected
        for (int indexProduitExp = 0;
             indexProduitExp < expListeProduits.size();
             indexProduitExp ++) {

            // Libelle
            assertEquals("[Echec -> Test Catalogue Controleur] élément de expected",
                    expListeProduits.get(indexProduitExp).getLibelle(),
                    actReponseListeProduits.getBody().get(indexProduitExp).getLibelle());

            // Prix
            assertEquals("[Echec -> Test Catalogue Controleur] élément de expected",
                    expListeProduits.get(indexProduitExp).getPrix(),
                    actReponseListeProduits.getBody().get(indexProduitExp).getPrix());

            // Categorie
            assertEquals("[Echec -> Test Catalogue Controleur] élément de expected",
                    expListeProduits.get(indexProduitExp).getCategorie(),
                    actReponseListeProduits.getBody().get(indexProduitExp).getCategorie());

            // Description
            assertEquals("[Echec -> Test Catalogue Controleur] élément de expected",
                    expListeProduits.get(indexProduitExp).getDescription(),
                    actReponseListeProduits.getBody().get(indexProduitExp).getDescription());

            // Image
            assertEquals("[Echec -> Test Catalogue Controleur] élément de expected",
                    expListeProduits.get(indexProduitExp).getImage(),
                    actReponseListeProduits.getBody().get(indexProduitExp).getImage());

            // PromotionIdCle
            assertEquals("[Echec -> Test Catalogue Controleur] élément de expected",
                    expListeProduits.get(indexProduitExp).getPromotionIdCle(),
                    actReponseListeProduits.getBody().get(indexProduitExp).getPromotionIdCle());
        }
    }
}


