package com.example.promotion.controleur;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.reponse.ReponseAdministrateur;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.AdministrateurService;
import com.example.promotion.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrateur")
@CrossOrigin(origins = "*")
public class AdministrateurControleur {
    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/connecter/{adresseMail}/{mdp}")
    public ResponseEntity<ReponseAdministrateur> connecterAdministrateur(@PathVariable("adresseMail") String adresseMail,
                                                                         @PathVariable("mdp") String motDePasse) {

        System.out.println("Connexion à l'adresse : " + adresseMail + " et mdp : " + motDePasse);

        // Récupère le résultat de la recherche dans la base de donnée des identifiants donnés.
        List<Administrateur> administrateur = administrateurService.connecterAdministrateur(adresseMail.toLowerCase(), motDePasse);

        ReponseAdministrateur reponseAdministrateur;

        // Vérifie si un administrateur a été trouvé dans la base de donnée avec les bons identifiants.
        if (!administrateur.isEmpty()) {
            // Connexion valide
            System.out.println("Admin found !");
            reponseAdministrateur = new ReponseAdministrateur(jwtTokenService.generateToken(administrateur.get(0)));
        } else {
            // Connexion invalide
            System.out.println("Admin not found !");
            reponseAdministrateur = new ReponseAdministrateur("KO");
        }
        return ResponseEntity.ok(reponseAdministrateur);
    }

    /**
     * Inscription d'un administrateur et enregistrement dans la base de donnée. Il est possible ici d'ajouter un code
     * administrateur à condition de modifier la base de donnée. Il servira à vérifier que l'inscription est valable.
     * @param administrateur Informations concernants l'administrateur.
     * @return Réponse relative à la réussite de l'inscription.
     */
    @PostMapping("/inscrire")
    public ResponseEntity<ReponseString> inscrireAdministrateur(@RequestBody Administrateur administrateur) {
        administrateurService.inscrireAdministrateur(administrateur);
        String message = "Création administrateur : " + administrateur.getPrenom() + " " + administrateur.getNom() + " terminé avec mail : " +
                administrateur.getAdresseMail() + ", mdp : " + administrateur.getMotDePasse() + " !";
        return ResponseEntity.ok(new ReponseString(message));
    }

    /**
     * Vérifie la validité d'un token envoyé par l'entête d'authorisation.
     * @param authorizationHeader L'entête d'authorisation qui contient le token à vérifier.
     * @return Réponse à envoyé au front. OK accès validé, KO accès non validé.
     */
    @GetMapping("/acces")
    public ResponseEntity<String> accesEspaceAdmin(@RequestHeader(name = "Authorization") String authorizationHeader) {

        // Si aucun token reçu, répond par un KO.
        if (authorizationHeader == null) { return ResponseEntity.ok("KO"); }

        // Si l'entête est différente de ce qui est attendu, répond par un KO.
        if (!authorizationHeader.startsWith("Bearer ")) { return ResponseEntity.ok("KO"); }

        // Vérifie la validité du token
        return ResponseEntity.ok(jwtTokenService.validatingToken(authorizationHeader.substring(7)));
    }


}
