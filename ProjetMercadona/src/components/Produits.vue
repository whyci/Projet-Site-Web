<template>

  <div class="grid grid-cols-1 md:grid-cols-5 gap-10">
    <template v-if="catalogue.length !== 0">
      <div v-for="(produit, index) in catalogue">
        <Produit :libelle="produit.libelle" :description="produit.description"
                 :categorie="produit.categorie" :prix="produit.prix" :nouveauprix="produit.nouveauprix" :image="produit.image"></Produit>
      </div>
    </template>
    <template v-else>
      Liste de produit vide
    </template>
  </div>
</template>

<script setup>
import Produit from "./ProduitElement.vue";
import {ref} from "vue";
// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';


const catalogue = ref([
  {id: 1, libelle: "Ratatouille", categorie: "Alimentaire", prix: 16, nouveauprix: 10, image: "./src/assets/Banque_images/Ratatouille.png"},
  {id: 2, libelle: "Tarte au citron", categorie: "Alimentaire", prix: 13, nouveauprix: 8, image: "./src/assets/Banque_images/Tarte au citron.png"},
  {id: 3, libelle: "Saumon risotto", categorie: "Alimentaire", prix: 16, nouveauprix: 10, image: "./src/assets/Banque_images/Saumon risotto.png"},
  {id: 4, libelle: "Serviettes", categorie: "Alimentaire", prix: 14, nouveauprix: "", image: "./src/assets/Banque_images/Serviettes.jpg"},
  {id: 5, libelle: "Teléphone", categorie: "Electronique", prix: 999, nouveauprix:"", image: "./src/assets/Banque_images/Tell.jpg"},
  {id: 6, libelle: "Peinture", categorie: "Electronique", prix: 35, nouveauprix: 30, image: "./src/assets/Banque_images/Peinture.jpg"},
  {id: 7, libelle: "Savon", categorie: "Electronique", prix: 6, nouveauprix: "", image: "./src/assets/Banque_images/savon.jpg"},
  {id: 8, libelle: "Ecran", categorie: "Electronique", prix: 600, nouveauprix: 500, image: "./src/assets/Banque_images/ecran.jpg"},
  {id: 9, libelle: "Jeux de carte", categorie: "Mobilier", prix: 8,nouveauprix: "", image: "./src/assets/Banque_images/jeux de carte.jpg"},
  {id: 10, libelle: "Citrouille", categorie: "Mobilier", prix: 10, nouveauprix: 8, image: "./src/assets/Banque_images/citrouille.jpg"},
  {id: 11, libelle: "CD", categorie: "Mobilier", prix: 25, nouveauprix: 15, image: "./src/assets/Banque_images/cd.jpg"},
  {id: 12, libelle: "Café", categorie: "Mobilier", prix: 6, nouveauprix: "", image: "./src/assets/Banque_images/café.jpg"}
]);

</script>
