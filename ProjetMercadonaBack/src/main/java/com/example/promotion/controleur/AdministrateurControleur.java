package com.example.promotion.controleur;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.Produit;
import com.example.promotion.reponse.ReponseAdministrateur;
import com.example.promotion.reponse.ReponseProduits;
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
        List<Administrateur> administrateur = administrateurService.connecterAdministrateur(adresseMail.toLowerCase(), motDePasse);

        // Gestion du token
        String token = jwtTokenService.generateToken(adresseMail.toLowerCase(), 3600000);

        // Réponse de la requête au client.
        ReponseAdministrateur reponseAdministrateur = new ReponseAdministrateur(administrateur, token);
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
        String message = "Création administrateur : " + administrateur.getPrenom() + " " + administrateur.getNom() + " terminé !";
        return ResponseEntity.ok(new ReponseString(message));
    }

}
