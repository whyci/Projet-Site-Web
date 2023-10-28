<template>

  <div class="grid grid-cols-1 md:grid-cols-5 gap-10">
    <template v-if="produitsEnPromotions.length !== 0 && produits.pageParent === 'Accueil'">
      <div v-for="(produit, index) in produitsEnPromotions">
        <Produit :id-produit="produit.id" :libelle="produit.libelle" :description="produit.description"
                 :categorie="produit.categorie" :prix="produit.prix" :nouveauprix="produit.nouveauprix"
                 :image="produit.image" :page-grand-parent="produits.pageParent"
                 :id-promotion-associe="produit.promotionIdCle" ></Produit>
      </div>
    </template>

    <template v-else-if="produits.pageParent === 'Accueil'" >
      <h2>Pas de promotions !</h2>
    </template>
    <!-- Si la liste resultatRechercheCategorie n'est pas nulle, on rentre dans la boucle for et affiche chaque produit
    contenu dans cette liste -->
    <template v-else-if="produits.resultatRechercheCategorie !== null">

      <!-- Affiche chaque produit contenu dans le résultat de la liste de la recherche -->
      <template v-if="produits.resultatRechercheCategorie.length !== 0">
        <div v-for="(produit, index) in produits.resultatRechercheCategorie">
          <Produit :id-produit="produit.id" :libelle="produit.libelle" :description="produit.description"
                   :categorie="produit.categorie" :prix="produit.prix" :nouveauprix="produit.nouveauprix"
                   :image="produit.image" :page-grand-parent="produits.pageParent"
                   :id-promotion-associe="produit.promotionIdCle" ></Produit>
        </div>
      </template>

      <!-- Si la liste de recherche est vide, on affiche chaque produit contenu dans le catalogue -->
      <template v-else-if="catalogue.length !== 0">
        <div v-for="(produit, index) in catalogue">
          <Produit :id-produit="produit.id" :libelle="produit.libelle" :description="produit.description"
                   :categorie="produit.categorie" :prix="produit.prix" :nouveauprix="produit.nouveauprix"
                   :image="produit.image" :page-grand-parent="produits.pageParent"
                   :id-promotion-associe="produit.promotionIdCle" ></Produit>
        </div>
      </template>

      <!-- Si la liste de la recherche et le catalogue sont vides, on affiche le message suivant -->
      <template v-else>
        Liste de produit vide
      </template>
    </template>

    <!-- Si la liste de la recherche est nulle, et que le catalogue n'est pas vide, on affiche le catalogue -->
    <template v-else-if="catalogue.length !== 0">
      <!-- Affiche chaque produit contenu dans le catalogue -->
      <div v-for="(produit, index) in catalogue">
        <Produit :id-produit="produit.id" :libelle="produit.libelle" :description="produit.description"
                 :categorie="produit.categorie" :prix="produit.prix" :nouveauprix="produit.nouveauprix"
                 :image="produit.image" :page-grand-parent="produits.pageParent"
                 :id-promotion-associe="produit.promotionIdCle" ></Produit>
      </div>
    </template>

    <!-- Si le catalogue est vide, affiche le message suivant -->
    <template v-else>
      Liste de produit vide
    </template>
  </div>
</template>

<script setup>
import Produit from "./ProduitElement.vue";
import {onMounted, ref} from "vue";
// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';
import store from "../store/index.js";
import Service from "../services/service.js";

/**
 * Props du composant Produits qui permet d'informer de quel composant il est appelé (de quel composant il est le fils).
 */
const produits = defineProps({
  pageParent: String,
  resultatRechercheCategorie: Array
});

// Récupère le catalogue stocké dans le store. Cela permet de ne pas solliciter le store à chaque appel du catalogue,
// mais qu'une seule fois à l'appel du composant Produits.
const catalogue = ref([]);
// Liste de tous les produits en promotions
const produitsEnPromotions = ref([]);
// Liste des différentes promotions
const promotions = ref([]);

const resultatRechercheCategorie = ref([]);

/**
 * On remplie la listePromotions des produits en promotion. On utilise des paramètres différents pour récupérer
 * les produits en promotions en fonction de la connexion avec le back.
 */
function creationListePromotion() {

  // Si connexion avec le back
  if (store.state.connexionBack) {
    // Récupère la liste des produits dans l'objet JSON du catalogue.
    let listeDesProduits = JSON.parse(JSON.stringify(catalogue.value));

    // On parcourt la liste catalogue. length récupère la taille de la liste (nombre d'éléments=produits dans la liste).
    for (let indexProduit = 0; indexProduit < listeDesProduits.length; indexProduit ++) {
      // Si le produit courant possède un nouveau prix, on ajoute ce produit dans la liste des promotions.
      if (listeDesProduits[indexProduit].promotionIdCle > 0) {
        // On ajoute le produit dans la liste des promotions.
        produitsEnPromotions.value.push(listeDesProduits[indexProduit]);
      }
    }
    store.state.catalogueEnPromotions = produitsEnPromotions.value;

  // Si pas de connexion avec le back
  } else {
    // On parcourt la liste catalogue. length récupère la taille de la liste (nombre d'éléments=produits dans la liste).
    for (let indexProduit = 0; indexProduit < catalogue._rawValue.length; indexProduit ++) {
      // Si le produit courant possède un nouveau prix, on ajoute ce produit dans la liste des promotions.
      if (catalogue._rawValue[indexProduit].nouveauprix > 0) {
        // On ajoute le produit dans la liste des promotions.
        produitsEnPromotions.value.push(catalogue._rawValue[indexProduit]);
      }
    }

  }
  console.log("Liste promotions : " + produitsEnPromotions.value);
}

function recupererCatalogue() {
  // Si connexion avec le back; récupère le catalogue et les promotions provenant du back. Sinon, il stocke en dur les
  // valeurs dans le code.
  if (store.state.connexionBack) {
    // Demande le catalague au back
    Service.serviceDemanderCatalogue()
      .then(response => {
        console.log("Catalogue :");
        console.log(response.data);
        // Enregistrement du catalogue dans la variable du composant et dans le store.
        catalogue.value = response.data;
        store.state.catalogue = response.data;

        // On crée la liste de promotion
        creationListePromotion();
      })
      .catch(e => {
        console.log(e);
      });

    // Demande des promotions
    if (store.state.promotions.length === 0) {
      Service.serviceDemanderPromotions()
        .then(response => {
          console.log("Promotions:");
          console.log(response.data);
          promotions.value = response.data;
          store.state.promotions = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    }

    // Cas de non connexion avec le back.
  } else {
    // Stocke en dur le catalogue.
    catalogue.value = [
      {id: 1, libelle: "Artichaut", categorie: "Alimentaire", prix: 6, nouveauprix: 0, description: "légume de saison", image: "./src/assets/Banque_images/artichaut.jpg"},
      {id: 2, libelle: "Dentifrice", categorie: "Hygiène", prix: 4, nouveauprix: 2, description: "Hygiène bucco dentaire", image: "./src/assets/Banque_images/dentifrice.jpg"},
      {id: 3, libelle: "Pains aux fruits secs", categorie: "Alimentaire", prix: 6, description: "Pains idéal pour l'apéro", nouveauprix: 4, image: "./src/assets/Banque_images/pains aux fruits secs.jpg"},
      {id: 4, libelle: "Serviettes", categorie: "Hygiène", prix: 14, nouveauprix: 0, description: "Linges de maison", image: "./src/assets/Banque_images/Serviettes.jpg"},
      {id: 5, libelle: "Teléphone", categorie: "Electronique", prix: 999, nouveauprix:0, description: "8 Giga RAM, 128 GO", image: "./src/assets/Banque_images/Tell.jpg"},
      {id: 6, libelle: "Peinture", categorie: "Loisirs", prix: 35, nouveauprix: 30, description: "Loisirs créatif", image: "./src/assets/Banque_images/Peinture.jpg"},
      {id: 7, libelle: "Savon", categorie: "Hygiène", prix: 6, nouveauprix: 0, description: "Hygiène corporel", image: "./src/assets/Banque_images/savon.jpg"},
      {id: 8, libelle: "Ecran", categorie: "Electronique", prix: 600, nouveauprix: 500, description: "Ecran 12 pouces", image: "./src/assets/Banque_images/ecran.jpg"},
      {id: 9, libelle: "Jeux de carte", categorie: "Loisirs", prix: 8,nouveauprix: 0, description: "Jeux de société", image: "./src/assets/Banque_images/jeux de carte.jpg"},
      {id: 10, libelle: "Citrouille", categorie: "Mobilier", prix: 10, nouveauprix: 8, description: "légume de saison", image: "./src/assets/Banque_images/citrouille.jpg"},
      {id: 11, libelle: "CD", categorie: "Musique", prix: 25, nouveauprix: 15, description: "Musique pop, rock, métal", image: "./src/assets/Banque_images/cd.jpg"},
      {id: 12, libelle: "Café", categorie: "Alimentaire", prix: 6, nouveauprix: 0, description: "Café goût intense", image: "./src/assets/Banque_images/café.jpg"}
    ];

    // Vérifier si l'on est appelé par la page Accueil
    if (produits.pageParent === "Accueil") {
      // On crée la liste de promotion
      creationListePromotion();
    }
  }
}

// Appelé systématiquement au chargement du composant.
onMounted(async () => {
  if (store.state.catalogue.length === 0) {
    recupererCatalogue();
  } else {
    catalogue.value = store.state.catalogue;
  }
});

</script>
