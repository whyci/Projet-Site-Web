package com.example.promotion.controleur;

import com.example.promotion.modele.Produit;
import com.example.promotion.reponse.ReponseString;
import com.example.promotion.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/produit")
@CrossOrigin(origins = "*")
public class ProduitControleur {

    /**
     * Instance de ProduitService, interface d'accès à la base de donnée concernant les produits.
     */
    private final ProduitService produitService;

    /**
     * Constructeur de la classe pour initialiser les instances de services qu'il utilise. Nécessaire pour les tests,
     * instancier les services mockés.
     * @param produitService Service de produit.
     */
    @Autowired
    public ProduitControleur(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Création d'un produit - Etape 1: Ajout d'un produit dans la base de donnée avec une image.
     * @param fichierImage envoyer sous forme de form-data qui contient l'image que l'on souhaite ajouter au nouveau produit.
     * @return Response entité qui est une chaine de caractere qui valide que la requête a été prise en compte et effectuée.
     * @throws IOException Exception relatif à form-data.
     */
    @PostMapping("/ajouter/image")
    public ResponseEntity<ReponseString> ajouterProduitImage(@RequestPart("image") MultipartFile fichierImage) throws IOException {

        Produit produit = new Produit();
        // Ajotuer une condition qui traite d'une eventuelle absence d'image. Si le nom du fichier s'appelle [quelque chose] on utilise
        // l'image par défaut qui est stocké dans ce projet. On crée quand même le produit et renvoie l'id du produit.
        produit.setImage(fichierImage.getBytes());
        Long idProduit = produitService.enregistrerProduitImage(produit);

        // Le message renvoyé au front comprend un '=' avant l'id du produit, il est nécessaire pour récupérer
        // correctement l'id du produit dans le front
        String message = "="+idProduit;
        return ResponseEntity.ok(new ReponseString(message));
    }

    /**
     * Création d'un produit - Etape 2: Association des paramètres du produit que l'on souahite ajouter.
     * @param produit que l'on souhaite ajouter (avec ses paramètres: libelle, catégorie, description, prix).
     * @param id du produit que l'on ajoute.
     * @return Réponse ...
     */
    @PostMapping("/ajouter/produit/{id}")
    public ResponseEntity<ReponseString> ajouterProduitParametres(@RequestBody Produit produit,
                                                                  @PathVariable("id") Long id) {
        System.out.println("Produit paramètres : "+produit.getLibelle());
        produitService.enregistrerProduitParametres(produit, id);
        String message = "Ajout Produit OK";
        return ResponseEntity.ok(new ReponseString(message));
    }
}

