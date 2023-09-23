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

const catalogue = ref([
  {id: 1, libelle: "Artichaut", categorie: "Alimentaire", prix: 6, nouveauprix: "", image: "./src/assets/Banque_images/artichaut.jpg"},
  {id: 2, libelle: "Dentifrice", categorie: "Hygiène", prix: 4, nouveauprix: 2, image: "./src/assets/Banque_images/dentifrice.jpg"},
  {id: 3, libelle: "Pains aux fruits secs", categorie: "Alimentaire", prix: 6, nouveauprix: 4, image: "./src/assets/Banque_images/pains aux fruits secs.jpg"},
  {id: 4, libelle: "Serviettes", categorie: "Hygiène", prix: 14, nouveauprix: "", image: "./src/assets/Banque_images/Serviettes.jpg"},
  {id: 5, libelle: "Teléphone", categorie: "Electronique", prix: 999, nouveauprix:"", image: "./src/assets/Banque_images/Tell.jpg"},
  {id: 6, libelle: "Peinture", categorie: "Loisirs", prix: 35, nouveauprix: 30, image: "./src/assets/Banque_images/Peinture.jpg"},
  {id: 7, libelle: "Savon", categorie: "Hygiène", prix: 6, nouveauprix: "", image: "./src/assets/Banque_images/savon.jpg"},
  {id: 8, libelle: "Ecran", categorie: "Electronique", prix: 600, nouveauprix: 500, image: "./src/assets/Banque_images/ecran.jpg"},
  {id: 9, libelle: "Jeux de carte", categorie: "Loisirs", prix: 8,nouveauprix: "", image: "./src/assets/Banque_images/jeux de carte.jpg"},
  {id: 10, libelle: "Citrouille", categorie: "Mobilier", prix: 10, nouveauprix: 8, image: "./src/assets/Banque_images/citrouille.jpg"},
  {id: 11, libelle: "CD", categorie: "Musique", prix: 25, nouveauprix: 15, image: "./src/assets/Banque_images/cd.jpg"},
  {id: 12, libelle: "Café", categorie: "Alimentaire", prix: 6, nouveauprix: "", image: "./src/assets/Banque_images/café.jpg"}
]);

const produits = defineProps({
  pageParent: String,
});

const listePromotions = ref([]);

// On remplie la listePromotions des produits en promotion.
function creationListePromotion() {
  // On parcourt la liste catalogue. length récupère la taille de la liste (nombre d'éléments=produits dans la liste).
  for (let indexProduit = 0; indexProduit < catalogue.value.length; indexProduit ++) {
    // Si le produit courant possède un nouveau prix, on ajoute ce produit dans la liste des promotions.
    if (catalogue.value[indexProduit].nouveauprix > 0) {
      // On ajoute le produit dans la liste des promotions.
      listePromotions.value.push(catalogue.value[indexProduit]);
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
