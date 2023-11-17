/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.controleur;

import com.example.promotion.modele.CreationProduit;
import com.example.promotion.modele.Produit;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.ProduitService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Tests de produitControleur. Ils concernent l'ajout des produits en utilisant les deux étapes ; Envoi de l'image du
 * produit, puis envoi des informations du produit.
 * Note : Un contrôle des informations du produit est fait à la saisi de l'utilisateur. Il n'est pas nécessaire de
 * tester toutes les saisies possibles de l'utilisateur puisque cela est déjà géré en amont.
 */
public class TestProduitControleur {

    /**
     * Les mocks de fichier image que l'on utilise pour la première étape des tests de l'ajout de produit.
     * Les valeurs sont choisies arbitrairement, de même que le nombre de valeurs.
     */
    private final MultipartFile fichierImageRempli = new MockMultipartFile("Image remplie", new byte[]{
            0x1, 0x78, 0x64, 0x12, 0x51, 0x22, 0x69, 0x79, 0x1, 0x71, 0x21, 0x40, 0x32, 0x6, 0x0, 0x0,
            0x0, 0x04, 0x3, 0x70, 0x08, 0x24, 0x29, 0x26, 0x40, 0x4, 0x2, 0x7, 0x0, 0x35, 0x50, 0x40
    });
    private final MultipartFile fichierImageVide = new MockMultipartFile("Image vide", new byte[]{});

    /**
     * Instance du mock de ProduitService pour intercepter les appels de fonctions.
     */
    @Mock
    private ProduitService produitService;

    /**
     * Classe que l'on veut tester.
     */
    @InjectMocks
    @Resource
    private ProduitControleur produitControleur;

    /**
     * Préparation des tests et étapes exécutés systématiquement avant chaque fonction de test.
     */
    @BeforeEach
    public void prepTestProduitControleur() throws IOException {
        // Initialise le mock de produitService
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test d'ajout d'un produit avec une image remplie.
     */
    @Test
    public void testAjoutProduitImageRemplie() throws IOException {

        /* - - - - Données de test - - - - */
        // Id du produit
        Long idProduit1 = 1L;
        // Autres informations du produit
        Produit produit1 = CreationProduit.creationPartieDTProduit("Salade", 3.5F, "Aliment",
                "Salade iceberg, au kg, produit frais de saison");

        /* - - - - Comportement du mock - - - - */

        // Remplacement du résultat de enregistrerProduitImage en retournant l'id du produit 1
        when(produitService.enregistrerProduitImage(any(Produit.class)))
                .thenReturn(idProduit1);

        // Instance d'objet à tester
        produitControleur = new ProduitControleur(produitService);

        /* - - - - ETAPE 1 : Ajout d'image - - - - - */

        /* - - - - Exécution des tests - - - - - */

        ResponseEntity<ReponseString> resReponseEtape1AjoutImageProduit1 = produitControleur.ajouterProduitImage(fichierImageRempli);

        /* - - - - Assertions des résultats de tests - - - - */

        // Assertion du résultat de id produit que l'on obtient
        assertEquals("[Echec -> Test Produit Controleur] produit 1 - étape 1 ajout image",
                idProduit1, Long.parseLong(
                        Objects.requireNonNull(resReponseEtape1AjoutImageProduit1.getBody()).message().substring(1)
                ));

        // Vérifie que la fonction mockée a bien été appelée
        verify(produitService).enregistrerProduitImage(any(Produit.class));

        /* - - - - ETAPE 2 : Ajout paramètres - - - - - */

        /* - - - - Exécution des tests - - - - - */

        produitControleur.ajouterProduitParametres(produit1, idProduit1);

        /* - - - - Assertions des résultats de tests - - - - */

        // Vérifie que la fonction enregistrerProduitParametres du mock a bien été appelée avec les bons paramètres
        verify(produitService).enregistrerProduitParametres(produit1, idProduit1);
    }

    /**
     * Test d'ajout d'un produit avec une image vide.
     */
    @Test
    public void testAjoutProduitImageVide() throws IOException {

        /* - - - - Exécution des tests - - - - - */
        Long idProduit2 = 2L;

        // Produit sans image
        Produit produit2 = CreationProduit.creationPartieDTProduit("Paprika", 15.0F, "Condiment",
                "Epice douce");

        /* - - - - Comportement du mock - - - - */

        // Remplacement du résultat de enregistrerProduitImage en retournant l'id du produit 2
        when(produitService.enregistrerProduitImage(any(Produit.class)))
                .thenReturn(idProduit2);

        // Instance d'objet à tester
        produitControleur = new ProduitControleur(produitService);

        /* - - - - ETAPE 1 : Ajout d'image - - - - - */

        /* - - - - Exécution des tests - - - - - */

        ResponseEntity<ReponseString> resReponseEtape1AjoutImageProduit2 = produitControleur.ajouterProduitImage(fichierImageVide);

        /* - - - - Assertions des résultats de tests - - - - */

        assertEquals("[Echec -> Test Produit Controleur] produit 2 - étape 1 ajout image",
                idProduit2, Long.parseLong(
                        Objects.requireNonNull(resReponseEtape1AjoutImageProduit2.getBody()).message().substring(1)
                ));

        // Vérifie que la fonction mockée a bien été appelée
        verify(produitService).enregistrerProduitImage(any(Produit.class));

        /* - - - - ETAPE 2 : Ajout autres informations - - - - - */

        /* - - - - Exécution des tests - - - - - */

        produitControleur.ajouterProduitParametres(produit2, idProduit2);

        /* - - - - Assertions des résultats de tests - - - - */

        // Vérifie que la fonction enregistrerProduitParametres du mock a bien été appelée avec les bons paramètres
        verify(produitService).enregistrerProduitParametres(produit2, idProduit2);

    }
}
