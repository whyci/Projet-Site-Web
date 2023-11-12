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
import Service from "../services/index.js";

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

/**
 * On remplie la listePromotions des produits en promotion. On utilise des paramètres différents pour récupérer
 * les produits en promotions en fonction de la connexion avec le back.
 */
function creationListePromotion() {
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
}

function recupererCatalogue() {
  // Demande le catalague au back
  Service.serviceDemanderCatalogue()
    .then(response => {
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
        promotions.value = response.data;
        store.state.promotions = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  }
}

// Appelé systématiquement au chargement du composant.
onMounted(async () => {
  if (store.state.catalogue.length === 0) {
    recupererCatalogue();
  } else {
    catalogue.value = store.state.catalogue;
    creationListePromotion();
  }
});

</script>
