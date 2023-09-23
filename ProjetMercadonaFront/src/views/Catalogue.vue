<template>
  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte et font-bold de le mettre en gras;
       text-green-900 permet de mettre le texte en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Catalogue</h1>
    <br>
    <div>
      <p>Voici les produits en promotion de notre magasin toutes catégories confondus, l'alimentaire, traiteur, hygiènes, loisirs ...</p>
      <!-- br pour créer un espace en sautant une ligne -->
      <br> <br>
      <!-- permet de gérer la taille de la barre de recherche-->
      <div class="text-center">
        <!-- permet de gérer le type de données, de classe pour définir le design de la barre de recherche,
        placehorder permet de voir en gris clair dans la barre de recherche -->
        <input
            type="text"
            class="rounded border-2 bg-white border-gray-200 focus:border-green-800 w-50 "
            id="nomProduitRecherche"
            required
            v-model="nomProduitRecherche"
            name="rechercheProduit"
            placeholder="nom recherche categorie"
          />
      </div>
    </div>
    <!-- permet de gérer la taille de la barre de recherche-->
    <div class="text-center">
      <br>
      <!-- permet de gérer la bordure et l'espace du carré pour soumettre la demande
       btn, et btn-success permettent de créer un carré et le mettre en couleur
       les autres informations permettent de rechercher un produit selon sa catégorie
       quand l'on click sur la bouton la recherche est lancée-->
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="chercherProduit(nomProduitRecherche)">Rechercher categorie</button>
      <br><br/>
    </div>
    <!-- permet d'importer le fichier produits et d'afficher les données -->
  <Produits :page-parent="'Catalogue'"/>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
  <footer class="footer-bg">
  </footer>
</template>

<script setup>
import { onMounted } from "vue";
import Produits from "../components/Produits.vue";

// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';
import Catalogue from "./Catalogue.vue";

onMounted(() => {
});
</script>