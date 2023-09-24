<template>

  <div class="grid grid-cols-1 md:grid-cols-5 gap-10">
    <template v-if="listePromotions.length !== 0">
      <div v-for="(produit, index) in listePromotions">
        <Produit :libelle="produit.libelle" :description="produit.description"
                 :categorie="produit.categorie" :prix="produit.prix" :nouveauprix="produit.nouveauprix"
                 :image="produit.image" :page-grand-parent="produits.pageParent" ></Produit>
      </div>
    </template>
    <template v-else-if="catalogue.length !== 0">
      <div v-for="(produit, index) in catalogue">
          <Produit :libelle="produit.libelle" :description="produit.description"
                 :categorie="produit.categorie" :prix="produit.prix" :nouveauprix="produit.nouveauprix"
                 :image="produit.image" :page-grand-parent="produits.pageParent" ></Produit>
      </div>
    </template>
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

// Récupère le catalogue stocké dans le store. Cela permet de ne pas solliciter le store à chaque appel du catalogue,
// mais qu'une seule fois à l'appel du composant Produits.
const catalogue = ref(store.state.catalogue);

const produits = defineProps({
  pageParent: String,
});

const listePromotions = ref([]);

// On remplie la listePromotions des produits en promotion.
function creationListePromotion() {
  // On parcourt la liste catalogue. length récupère la taille de la liste (nombre d'éléments=produits dans la liste).
  for (let indexProduit = 0; indexProduit < catalogue.length; indexProduit ++) {
    // Si le produit courant possède un nouveau prix, on ajoute ce produit dans la liste des promotions.
    if (catalogue[indexProduit].nouveauprix > 0) {
      // On ajoute le produit dans la liste des promotions.
      listePromotions.value.push(catalogue[indexProduit]);
    }
  }
}

// Appelé systématiquement au chargement du composant.
onMounted(async () => {
  // Vérifier si l'on est appelé par la page Accueil
  if (produits.pageParent === "Accueil") {
    // On crée la liste de promotion
    creationListePromotion();
  }

});

</script>
