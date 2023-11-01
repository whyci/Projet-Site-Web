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
    private final Long idProduit1 = 1L;
    private final Long idProduit2 = 2L;
    private final MultipartFile fichierImageVide = new MockMultipartFile("Image vide", new byte[]{});

    /**
     * Identifiant du produit du test courant
     */
    private Long idProduitEnCours;

    @Mock
    private ProduitService produitService;

    @InjectMocks
    @Resource
    private ProduitControleur produitControleur;

    private Long recupereIdProduit() {
        return this.idProduitEnCours;
    }

    @BeforeEach
    public void prepTestProduitControleur() throws IOException {

        // Initialise le mock de produitService
        MockitoAnnotations.initMocks(this);

        /* - - - - Données de test (P = Produit) - - - - */

        // Produit avec une image
        Produit produit1 = CreationProduit.creationPartieDTProduit("Salade", 3.5F, "Aliment",
                "Salade iceberg, au kg, produit frais de saison");

        // Produit sans image
        Produit produit2 = CreationProduit.creationPartieDTProduit("Paprika", 15.0F, "Condiment",
                "Epice douce");

        /* - - - - Comportement du mock - - - - */

        // Remplace le comportement de la fonction enregistrerProduitImage pour renvoyer les id des produits en données de tests
        when(produitService.enregistrerProduitImage(any(Produit.class)))
                .thenReturn(idProduit1);
    }

    /**
     * Test d'ajout des produits
     */
    @Test
    public void testAjoutProduit() throws IOException {

        /* - - - - Exécution des tests - - - - - */
        produitControleur = new ProduitControleur(produitService);
        ResponseEntity<ReponseString> resReponseAjoutImageProduit1 = produitControleur.ajouterProduitImage(fichierImageRempli);

        produitControleur = new ProduitControleur(produitService);
        ResponseEntity<ReponseString> resReponseAjoutImageProduit2 = produitControleur.ajouterProduitImage(fichierImageVide);

        /* - - - - Assertions des résultats de tests - - - - */
        //this.idProduitEnCours = idProduit1;
        assertEquals("[Echec -> Test Produit Controleur] produit 1 - étape 1 ajout image",
                idProduit1, Long.parseLong(
                        Objects.requireNonNull(resReponseAjoutImageProduit1.getBody()).message().substring(1)
                ));

        when(produitService.enregistrerProduitImage(any(Produit.class)))
                .thenReturn(idProduit2);
        assertEquals("[Echec -> Test Produit Controleur] produit 2 - étape 1 ajout image",
                idProduit2, Long.parseLong(
                        Objects.requireNonNull(resReponseAjoutImageProduit2.getBody()).message().substring(1)
                ));

    }
}
