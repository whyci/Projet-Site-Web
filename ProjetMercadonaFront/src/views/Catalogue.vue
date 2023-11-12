<!--
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
-->
<template>
  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte et font-bold de le mettre en gras;
       text-green-900 permet de mettre le texte en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Catalogue</h1>
    <br>
    <div>
      <p>Voici tous les produits de notre magasin l'alimentaire, traiteur, hygiènes, loisirs ...</p>
      <!-- br pour créer un espace en sautant une ligne -->
      <br> <br>
      <!-- permet de gérer la taille de la barre de recherche-->
      <div class="text-center">
        <!-- permet de gérer le type de données, de classe pour définir le design de la barre de recherche,
        placehorder permet de voir en gris clair dans la barre de recherche -->
        <input
            type="text"
            class="rounded border-2 border-gray-200 focus:border-green-900 bg-white w-50 "
            id="rechercheCategorie"
            required
            v-model="rechercheCategorie"
            name="rechercheProduit"
            placeholder="Recherche par categorie"
          />
      </div>
    </div>
    <!-- permet de gérer la taille de la barre de recherche-->
    <div class="text-center">
      <br>
      <!-- permet de gérer l'espace du carré pour soumettre la demande btn, et btn-success permettent de créer un carré
      et le mettre en couleur. Les autres informations permettent de rechercher un produit selon sa catégorie
       quand l'on click sur la bouton la recherche est lancée-->
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="resultatRechercheCategorie=filtreCategorie(ancienFiltre, rechercheCategorie)">Lancer la recherche
      </button>
    </div>
    <br><br>

    <!-- permet d'appeller le fichier produits et d'afficher les données -->
    <Produits :page-parent="'Catalogue'" :resultat-recherche-categorie="resultatRechercheCategorie"/>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
  <footer class="footer-bg">
  </footer>
</template>

<script setup>
import {onMounted, ref} from "vue";
import Produits from "../components/Produits.vue";

// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';
import {filtreCategorie} from "../filters/index.js";

const rechercheCategorie = ref("");

// Liste vide, dans le cas où l'on souhaiterait appliquer plusieurs filtres en même temps, il faudra reprendre cette variable.
const ancienFiltre = ref([]);
const resultatRechercheCategorie = ref([]);

// Tableau des produits filtrés.
const filtre = ref([]);

onMounted(() => {
});
</script>
