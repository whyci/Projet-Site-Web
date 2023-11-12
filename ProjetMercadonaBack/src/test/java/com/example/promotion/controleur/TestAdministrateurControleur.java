package com.example.promotion.controleur;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.reponse.ReponseAdministrateur;
import com.example.promotion.service.AdministrateurService;
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
 * Tests de produitControleur. Ils concernent l'ajout des produits en utilisant les deux étapes ; Envoi de l'image du
 * produit, puis envoi des informations du produit.
 * Note : Un contrôle des informations du produit est fait à la saisi de l'utilisateur. Il n'est pas nécessaire de
 * tester toutes les saisies possibles de l'utilisateur puisque cela est déjà géré en amont.
 */
public class TestAdministrateurControleur {

    /**
     * Donnée de test : mails que l'on utilise en tant que données de test.
     */
    private final String expMail = "mail.merca@domaine.com";

    /**
     * Donnée de test : mot de passe que l'on utilise en tant que données de test.
     */
    private final String expMdp = "Cec13St.unMdp!";

    /**
     * Donnée de test : Token que l'on s'attend à recevoir pour la connexion et l'accès aux ressources de l'espace admin.
     */
    private final String expJWToken = "4v794av5b69z8ag5b578vqvvnoUCZUvcicOCnoVEZUB";

    /**
     * Donnée de test : Réponse "KO" pour une connexion incorrecte.
     */
    private final String expKO = "KO";

    /**
     * Instance du mock de Administrateur pour intercepter les appels de fonctions.
     */
    @Mock
    private AdministrateurService administrateurService;

    /**
     * Instance du mock de JwtTokenService pour intercepter les appels de fonctions.
     */
    @Mock
    private JwtTokenService jwtTokenService;

    /**
     * Instance de AdministrateurControleur, objet que l'on teste.
     */
    @InjectMocks
    @Resource
    private AdministrateurControleur administrateurControleur;

    /**
     * Préparation des tests et étapes exécutés systématiquement avant chaque fonction de test.
     */
    @BeforeEach
    public void prepTestAdminControleur() {

        // Initialise le mock de produitService
        MockitoAnnotations.openMocks(this);

        // Instance d'objet à tester
        administrateurControleur = new AdministrateurControleur(administrateurService, jwtTokenService);
    }

    /**
     * Test d'inscription d'un administrateur.
     */
    @Test
    public void testInscriptionAdmin() {

        /* - - - - Données de test - - - - */

        // Admin que l'on veut inscrire et que l'on s'attend à transmettre au service (Exp pour expected)
        final Administrateur adminInscriptionExp = creationDTAdmin();

        /* - - - - Exécution des tests - - - - - */

        administrateurControleur.inscrireAdministrateur(adminInscriptionExp);

        /* - - - - Assertions des résultats de tests - - - - */

        // Vérifie que la fonction inscrireAdministrateur du mock a bien été appelée avec l'administrateur donné
        verify(administrateurService).inscrireAdministrateur(adminInscriptionExp);
    }

    /**
     * Crée l'administrateur pour les tests. La génération de l'identifiant de l'administrateur est automatiquement
     * pris en charge par Spring du fait de l'auto-incrémentation, on ne le teste pas.
     * @return Instance de Administrateur que l'on créé.
     */
    private Administrateur creationDTAdmin() {
        // Création de l'instance
        Administrateur admin = new Administrateur();

        // Affecte l'adresse mail en fonction des paramètres de la fonction
        admin.setAdresseMail(expMail);
        admin.setMotDePasse(expMdp);
        admin.setDateNaissance(new Date());
        admin.setCivilite("Monsieur");
        admin.setNom("Nom");
        admin.setPrenom("Prénom");
        admin.setNumeroTelephone("0612345678");

        return admin;
    }

    /**
     * Test de connexion d'un administrateur.
     */
    @Test
    public void testConnexionAdmin() {

        /* - - - - Données de test - - - - */

        // Liste d'administrateur que l'on récupère par administrateurService
        List<Administrateur> listeAdmin = new ArrayList<>();

        // Instance des administrateurs
        Administrateur adminConnexionExp = creationDTAdmin();

        // Ajouts des administrateurs dans les listes
        listeAdmin.add(adminConnexionExp);


        /* - - - - Comportement du mock - - - - */

        // Remplacement du résultat de enregistrerProduitImage en retournant l'id du produit 1
        when(administrateurService.connecterAdministrateur(expMail, expMdp))
                .thenReturn(listeAdmin);

        // Remplacement du résultat de enregistrerProduitImage en retournant l'id du produit 1
        when(jwtTokenService.genererToken(any(Administrateur.class)))
                .thenReturn(expJWToken);

        /* - - - - Exécution des tests - - - - - */

        ResponseEntity<ReponseAdministrateur> resReponseConnexionAdminOK =
                administrateurControleur.connecterAdministrateur(expMail, expMdp);

        ResponseEntity<ReponseAdministrateur> resReponseConnexionAdminKOMail =
                administrateurControleur.connecterAdministrateur("mail@incorrect.com", expMdp);

        ResponseEntity<ReponseAdministrateur> resReponseConnexionAdminKOMdp =
                administrateurControleur.connecterAdministrateur(expMail, "Ceci3S1;UNmd!P");

        ResponseEntity<ReponseAdministrateur> resReponseConnexionAdminKO =
                administrateurControleur.connecterAdministrateur("mail@incorrect.com", "Ceci3S1;UNmd!P");

        ResponseEntity<ReponseAdministrateur> resReponseConnexionAdminKOMailVide =
                administrateurControleur.connecterAdministrateur("", expMdp);

        ResponseEntity<ReponseAdministrateur> resReponseConnexionAdminKOMdpVide =
                administrateurControleur.connecterAdministrateur(expMail, "");

        ResponseEntity<ReponseAdministrateur> resReponseConnexionAdminKOVide =
                administrateurControleur.connecterAdministrateur("", "");

        /* - - - - Assertions des résultats de tests - - - - */

        // Connexion réussie
        assertEquals("[Echec -> Test Administrateur Controleur] connexion réussie",
                expJWToken, Objects.requireNonNull(resReponseConnexionAdminOK.getBody()).token()
        );

        // Vérifie que la fonction inscrireAdministrateur du mock a bien été appelée avec l'administrateur donné
        verify(administrateurService).connecterAdministrateur(expMail, expMdp);

        // Connexion non-réussie
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - mail incorrect",
                expKO, Objects.requireNonNull(resReponseConnexionAdminKOMail.getBody()).token()
        );
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - mdp incorrect",
                expKO, Objects.requireNonNull(resReponseConnexionAdminKOMdp.getBody()).token()
        );
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - mail et mdp incorrects",
                expKO, Objects.requireNonNull(resReponseConnexionAdminKO.getBody()).token()
        );
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - mail vide",
                expKO, Objects.requireNonNull(resReponseConnexionAdminKOMailVide.getBody()).token()
        );
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - mdp vide",
                expKO, Objects.requireNonNull(resReponseConnexionAdminKOMdpVide.getBody()).token()
        );
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - mail et mdp vide",
                expKO, Objects.requireNonNull(resReponseConnexionAdminKOVide.getBody()).token()
        );

        // Vérifie que l'on ne l'a pas réappelé (soit 1 fois)
        verify(administrateurService, times(1)).connecterAdministrateur(expMail, expMdp);
    }


    /**
     * Test d'accès à l'espace admin pour un administrateur
     */
    @Test
    public void testAccesEspaceAdmin() {

        /* - - - - Données de test - - - - */

        // Les entêtes d'autorisation, valide ou non, contenant le token précédé du préfix "Bearer "
        String expEnteteAutorisationTokenOK = "Bearer " + expJWToken;
        String expEnteteAutorisationTokenKOVide = "";
        String expEnteteAutorisationTokenKOToken = "Bearer MauvaisToken111vah4vabeb654b8d8b64";

        // Réponse OK attendue.
        String expOK = "OK";

        /* - - - - Comportement du mock - - - - */

        // Remplacement du résultat de enregistrerProduitImage en retournant l'id du produit 1
        when(jwtTokenService.validerToken(anyString()))
                .thenReturn(expKO);

        when(jwtTokenService.validerToken(expJWToken))
                .thenReturn(expOK);

        /* - - - - Exécution des tests - - - - - */

        ResponseEntity<String> resAccesOK =
                administrateurControleur.accesEspaceAdmin(expEnteteAutorisationTokenOK);

        ResponseEntity<String> resAccesKOVide =
                administrateurControleur.accesEspaceAdmin(expEnteteAutorisationTokenKOVide);

        ResponseEntity<String> resAccesKOPrefix =
                administrateurControleur.accesEspaceAdmin(expJWToken);

        ResponseEntity<String> resAccesKOToken =
                administrateurControleur.accesEspaceAdmin(expEnteteAutorisationTokenKOToken);

        /* - - - - Assertions des résultats de tests - - - - */

        // Accès à l'espace admin autorisé
        assertEquals("[Echec -> Test Administrateur Controleur] connexion réussie",
                expOK, Objects.requireNonNull(resAccesOK.getBody())
        );

        // Accès à l'espace admin non autorisé
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - Champs vide",
                expKO, Objects.requireNonNull(resAccesKOVide.getBody())
        );
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie - Préfix vide",
                expKO, Objects.requireNonNull(resAccesKOPrefix.getBody())
        );
        assertEquals("[Echec -> Test Administrateur Controleur] connexion non réussie -Token incorrect",
                expKO, Objects.requireNonNull(resAccesKOToken.getBody())
        );

        // Vérifie que la fonction inscrireAdministrateur du mock a bien été appelée avec l'administrateur donné
        verify(jwtTokenService, times(2)).validerToken(anyString());
    }
}
