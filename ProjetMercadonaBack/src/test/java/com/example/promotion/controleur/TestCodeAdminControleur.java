/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.controleur;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.CodeAdmin;
import com.example.promotion.reponse.ReponseAdministrateur;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.AdministrateurService;
import com.example.promotion.service.CodeAdminService;
import com.example.promotion.service.JwtTokenService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Tests de codeAdminControleur. Ils concernent l'ajout des codeAdmin dans la base de donnée avec les situations
 * suivantes : le code donné est nul, un code existe déjà dans la base de donnée et l'ajout est réussi.
 */
public class TestCodeAdminControleur {

    /**
     * Instance du mock de CodeAdminService pour intercepter les appels de fonctions.
     */
    @Mock
    private CodeAdminService codeAdminService;

    /**
     * Instance de AdministrateurControleur, objet que l'on teste.
     */
    @InjectMocks
    @Resource
    private CodeAdminControleur codeAdminControleur;

    /**
     * Test d'inscription d'un administrateur en cas de réussite.
     */
    @Test
    public void testInscriptionAdminOK() {
        /* - - - - Pré-requis - - - - */
        // Initialise le mock de produitService
        MockitoAnnotations.openMocks(this);

        // Instance d'objet à tester
        codeAdminControleur = new CodeAdminControleur(codeAdminService);

        /* - - - - Données de test - - - - */
        // Réponse attendue pour code vide
        String expKOCodeVide = "KO_code_vide";

        // Réponse attendue pour code déjà existant dans la base de donnée
        String expKOCodeExistant = "KO_code_existant";

        // Réponse attendue pour ajout réussi
        String expOK = "OK";

        // Code admin que l'on donne pour un ajout correct dans la base de donnée
        String codeAdminOK = "hnhIAUDOnhho4624ez98g2z4";
        List<CodeAdmin> listeVide = new ArrayList<>();

        // code qui existe déjà dans la base de donnée. Sa valeur n'importe pas, tant qu'elle est différente de celle
        // que l'on souhaite ajouter correctement.
        String codeDejaExistant = "cezhvoNICOZVOI156vzv29";

        // Liste qui contient un codeAdmin (pas besoin d'y avoir de propriétés dans le codeAdmin)
        List<CodeAdmin> listeCodeAdminDejaExistant = new ArrayList<>();
        listeCodeAdminDejaExistant.add(new CodeAdmin());

        /* - - - - Comportement du mock - - - - */

        // Remplacement du résultat de verifierDisponibitilite en retournant la liste contenant le codeAdmin pour code
        // déjà existant
        when(codeAdminService.verifierDisponibitilite(codeDejaExistant))
                .thenReturn(listeCodeAdminDejaExistant);

        // Remplacement du résultat de verifierDisponibitilite en retournant la liste vide pour ajout réussi
        when(codeAdminService.verifierDisponibitilite(codeAdminOK))
                .thenReturn(listeVide);

        /* - - - - Exécution des tests - - - - - */

        ResponseEntity<ReponseString> resAjoutCodeAdminCodeNul =
                codeAdminControleur.ajouterCodeAdmin("");

        ResponseEntity<ReponseString> resAjoutCodeAdminCodeDejaExistant =
                codeAdminControleur.ajouterCodeAdmin(codeDejaExistant);

        ResponseEntity<ReponseString> resAjoutCodeAdminOK =
                codeAdminControleur.ajouterCodeAdmin(codeAdminOK);

        /* - - - - Assertions des résultats de tests - - - - */

        // Assert réponse code vide
        assertEquals("[Echec -> Test Code Admin Controleur] ajout code admin - code vide",
                expKOCodeVide,
                Objects.requireNonNull(resAjoutCodeAdminCodeNul.getBody()).message());

        // Assert réponse code déjà existant
        assertEquals("[Echec -> Test Code Admin Controleur] ajout code admin - code déjà existant",
                expKOCodeExistant,
                Objects.requireNonNull(resAjoutCodeAdminCodeDejaExistant.getBody()).message());

        // Assert réponse code déjà existant
        assertEquals("[Echec -> Test Code Admin Controleur] ajout code admin - code OK",
                expOK,
                Objects.requireNonNull(resAjoutCodeAdminOK.getBody()).message());

        // Vérifie que la fonction verifierDisponibitilite du mock a bien été appelée avec le code admin donné
        verify(codeAdminService, times(2)).verifierDisponibitilite(anyString());

        // Vérifie que la fonction ajoutCodeAdmin du mock a bien été appelée avec le code admin donné
        verify(codeAdminService).ajoutCodeAdmin(codeAdminOK);

        // Vérifie que la fonction ajoutCodeAdmin du mock n'a pas été appelé avec les codes déjà existant et nul
        verify(codeAdminService, never()).ajoutCodeAdmin(codeDejaExistant);
        verify(codeAdminService, never()).ajoutCodeAdmin("");
    }
}
