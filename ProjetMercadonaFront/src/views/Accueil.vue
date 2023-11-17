<!--
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
-->
<template>
  <div class="p-10 ">
    <!-- text-4xl permet d'agrandir le texte et font-bold de le mettre en gras;
   text-green-900 permet de mettre le texte en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Voici les produits en promotions !</h1>
    <br>
    <p>Voici les produits en promotion de notre magasin toutes catégories confondus, l'alimentaire, traiteur, hygiènes, loisirs ...</p>
    <br><br>
    <div>
      <!-- permet d'importer le fichier produits et d'afficher les données -->
      <produits :page-parent="'Accueil'" :resultat-recherche-categorie="null"/>
    </div>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
  <footer class="footer-bg">
  </footer>

</template>

<script setup>

import { computed, onMounted, ref } from "vue";
import store from "../store/index.js";

// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';
import Produits from "../components/Produits.vue";



</script>

<style>

.footer-bg {
  background-image: url('/nat.png');
  background-position: center;
  background-repeat: repeat;
  min-height:80px;
  min-width:1400px;
  margin-left: 0;
}
</style>
