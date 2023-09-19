<template>
  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Espace administrateur</h1>
    <br>
    <div>
  <p>Veuillez remplir le formulaire ci-dessous pour créer un produit.</p>
    <br>
  </div>
  <div class="">
    <div v-if="!submitted">
      <div class="form-group">
        <br>
        <input
          type="text"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="title"
          required_
          v-model="ajoutProduit.libelle"
          name="libelle"
          placeholder="Ecrire le libelle du produit *"
        />
        <br>
        <input
          type="text"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="description"
          required
          v-model="ajoutProduit.categorie"
          name="categorie"
          placeholder="Ecrire la catégorie du produit *"
        />
        <br>
        <input
          type="text"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="prix"
          required
          v-model="ajoutProduit.prix"
          name="prix"
          placeholder="Ecrire le prix du produit *"
        />
        <br>
        <input
          type="text"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-50"
          id="description"
          required
          v-model="ajoutProduit.description"
          name="description"
          placeholder="Ecrire la description du produit *"
        />
      </div>
      <div class="">
      <br>
        <!-- permet de gérer la bordure et l'espace du carré pour soumettre la demande
        btn, et btn-success permettent de créer un carré et le mettre en couleur
        les autres informations permettent de rechercher un produit selon sa catégorie
        quand l'on click sur la bouton ça valide le formulaire -->
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="ajouterProduit">Submit</button>
      <br><br>
      </div>
    </div>
  </div>
  <br>
  <!--
    <template v-if="!askedPromo">
        <input
          type="text"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="idProduitPromotionString"
          required
          v-model="idProduitPromotionString"
          name="idProduitPromotion"
          placeholder="UN TRUC EN ATTENDANT"
        />
      <br>
    <div class="text-center">
    <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="demanderPromo">Demander promo</button>
      <br><br/>
    </div>
  </template>
  <template v-else>
    <Promotion :id="idProduitPromotionInt" :ancien-prix="ancienPrixPromo"></Promotion>
    <button style="border : 1px solid #000; padding : 5px;
  max-width: 300px;
  margin: auto;"
            class="btn btn-success" @click="terminerPromo">Terminer promo</button>
    <br><br/>

  </template>
  -->

    <!-- permet d'importer le fichier produits et d'afficher les données -->
<Produits :page-parent="'EspaceAdministrateur'"/>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
  <footer class="footer-bg">
  </footer>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import Produits from "../components/Produits.vue";
import Produit from "../components/ProduitElement.vue";
import Catalogue from "./Catalogue.vue";
import EspaceAdministrateur from "./EspaceAdministrateur.vue";

const idProduitPromotionString = ref("");
const idProduitPromotionInt = ref(0);
const askedPromo = ref(false);
const ancienPrixPromo = ref(0);
const submitted = ref(false);

// Produit que l'on souhaite ajouter
const ajoutProduit = ref({
  id: 0,
  libelle: "",
  description: "",
  categorie: "",
  prix: 0
});

function ajouterProduit() {
  catalogue.value.push(ajoutProduit.value);
  submitted.value = true;
}

/**
 On récupère l'ancien prix du produit, que l'on souhaite appliquer une promotion, dans le catalogue que l'on possède.
 L'objectif est d'éviter les requêtes inutiles à la base de donnée.
 */
function recupereAncienPrix() {
  // On parcours les index du catalogue pour recuperer les id des produits.
  let i;
  for (i = 0; i < catalogue.value.length; i ++) {

    // On cree une variable locale qui stocke l'id courant du produit.
    // JSON.parse(JSON.stringify()) recupere chaque entite produit contenu dans le catalogue.
    let idProduitCourant = JSON.parse(JSON.stringify(catalogue.value[i])).id;

    // On verifie ensuite s'il est egal a l'id du produit que l'on souhaite appliquer une promotion.
    if (idProduitCourant === idProduitPromotionInt.value) {
      // On récupère la valeur de l'ancien prix.
      ancienPrixPromo.value = JSON.parse(JSON.stringify(catalogue.value[i])).prix;
    }
  }
}

function demanderPromo() {
  idProduitPromotionInt.value = parseInt(idProduitPromotionString.value);
  askedPromo.value = true;
  recupereAncienPrix();
}

function terminerPromo() {
  askedPromo.value = false;
}
</script>