package com.example.promotion.controleur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.reponse.ReponseAdministrateur;
import com.example.promotion.service.AdministrateurService;
import com.example.promotion.service.JwtTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

/**
 * Suite de tests de administrateurControleur. Ils concernent la connexion des administrateurs, leurs inscriptions,
 * la vérification d'accès aux ressources liées à l'espace admin.
 */
@WebMvcTest(AdministrateurControleur.class)
public class TestAdministrateurControleur1 {

    /**
     * Outil de mock d'un système MVC
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mock de administrateurService pour tester uniquement administrateurControleur
     */
    @MockBean
    private AdministrateurService administrateurService;

    /**
     * Mock de jwtTokenService pour générer un token
     */
    @MockBean
    private JwtTokenService jwtTokenService;

    /**
     * Mock de reponseAdministrateur pour renvoyer la réponse de la requête au front
     */
    @MockBean
    private ReponseAdministrateur reponseAdministrateur;

    private Administrateur admin_ent;

    @BeforeEach
    void beforeTestAdministrateurControleur() {
        admin_ent = new Administrateur();
    }

    /**
     * Test de la connexion administrateur
     * Note : On ne mock pas rseAdministrateur car étant un record, son utilisation (sans mock) peut faire partie d'un
     * résultat actual et expected
     */
    @Test
    void testConnexionAdmin() throws Exception {
        /* - - - - Données de test - - - - */
        // Donnée d'entrées
        String mailConnexionOK = "maurice.poirreau@gmail.com";
        String mdpConnexionOK = "mdpMAURICE";

        String mailConnexionKO = "jean.patrick@gmail.com";
        String mdpConnexionKO = "mdpJEAN";

        // Mock de administrateurService
        List<Administrateur> reponseConnecterAdministrateur = new ArrayList<>();
        reponseConnecterAdministrateur.add(new Administrateur());
        when(administrateurService.connecterAdministrateur(anyString(), anyString()))
                .thenReturn(reponseConnecterAdministrateur);

        // Mock de jwtTokenService
        String tokenGenere = "/TOKEN123genere!?::/";
        when(jwtTokenService.generateToken(admin_ent))
                .thenReturn(tokenGenere);

        ResponseEntity<ReponseAdministrateur> reponseExpConnexionOk = new ResponseEntity<>(ResponseEntity.ok(
                new ReponseAdministrateur(tokenGenere)).getStatusCode());

        /* - - - - Exécution des tests - - - - */
        mockMvc.perform(get("/administrateur/connecter/{adresseMail}/{mdp}", mdpConnexionOK, mdpConnexionKO))
                .andExpect((ResultMatcher) reponseExpConnexionOk);

        /* - - - - Assertions des résultats de tests - - - - */

    }

}
