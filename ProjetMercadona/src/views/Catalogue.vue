<template>
  <div class="p-10">
    <h1 class="text-4xl font-bold text-green-900">Catalogue</h1>
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <br>
    <div>
      <p>Veuillez remplir le formulaire ci-dessous afin de vous créer un compte les informations comportants un signe * sont obligatoire.</p>
      <br> <br>
      <div class="text-center">
        <!-- permet de gérer la taille de la barrière -->
        <input
            type="text"
            class="rounded border-2 bg-white border-gray-200 focus:border-green-800 w-50 "
            id="nomProduitRecherche"
            required
            v-model="nomProduitRecherche"
            name="rechercheProduit"
            placeholder="nom recherche categorie"
          />
          <!-- permet de gérer le type de données, de classe, l'ID etc pour faire fonctionner la recherche -->
      </div>

    </div>
    <div class="text-center">
      <br>
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="chercherProduit(nomProduitRecherche)">Rechercher categorie</button>
      <!-- permet de gérer la bordure et l'espace du carré pour soumettre la demande
        btn, et btn-success permettent de créer un carré et le mettre en couleur
        les autres informations permettent de rechercher un produit selon sa catégorie-->
      <br><br/>
    </div>

  <Produits />
  </div>
  <!-- permet d'importer le fichier produits et d'afficher les données -->
  <footer class="footer-bg">
  </footer>
</template>

<script setup>
import { onMounted } from "vue";
import Produits from "../components/Produits.vue";
import 'bootstrap/dist/css/bootstrap.min.css';
/* Importe la framework Bootstrap 5 qui permet d'améliorer le front du document */

onMounted(() => {
});
</script>