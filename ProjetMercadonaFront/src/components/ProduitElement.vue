<template>
  <div class="p-3 bg-white shadow rounded-xl hover:scale-105 transition-all h-96">
    <div class="">
      <!-- permet de gérer l'appel de l'image et son affichage -->
       <template v-if="store.state.connexionBack">
        <!-- permet de gérer l'affichage de l'image, gérer les bordures, la taille etc -->
        <img class="rounded-xl border-8 border-white ml-auto h-40 mr-auto" v-if="imageReconstruit !== null" :src="imageReconstruit" alt="Image" />
        <img v-else alt="IMAGE NULLE !" src="">
      </template>
      <template v-else>
        <!-- permet de gérer l'affichage de l'image, gérer les bordures, la taille etc -->
        <img class="rounded-xl border-8 border-white ml-auto h-40 mr-auto" v-if="produit.image" :src="image" alt="Select image">
      </template>
    </div>
    <br>

    <!-- permet de gérer la taille, mettre en gras et centrer les libelles -->
    <p class="font-bold text-xl-center text-2xl">{{ produit.libelle }}</p>
    <!-- permet de gérer la taille, mettre en semi gras et centrer les categories -->
    <p class="font-semibold text-xl-center">Categorie : {{ produit.categorie }}</p>
    <!-- permet de gérer la taille, centrer les descriptions et limiter la visibilité de la description à 20 caractères-->
    <p class="mb-2 text-center">
          {{ $filters.truncateWords( produit.description , 20) }}
    </p>
    <template v-if="!store.state.connexionBack">
      <!-- permet de mettre en gras et rayer le prix s'il y a une promotion -->
      <p v-if="produit.nouveauprix" class="font-bold text-decoration-line-through">{{ produit.prix }}€ </p>
      <!-- permet de mettre en gras et gérer la taille du texte s'il n'y a pas de promotion -->
      <p v-else class="font-bold text-2xl ">{{ produit.prix }}€ </p>

      <!-- permet de mettre en gras, mettre en rouge et gérer la taille du texte de la promotion -->
      <p v-if="produit.nouveauprix >0" class="font-bold text-red-600 text-3xl">{{ produit.nouveauprix }}€</p>

      <!-- permet d'afficher le nouveau prix s'il existe une promotion associé au produit -->
      <p v-else-if="saisiNouveauPrix > 0" class="font-bold text-red-600 text-3xl">{{ saisiNouveauPrix }}€</p>
    </template>

    <template v-else>
      <!-- Vérifie s'il existe une promotion au produit -->
      <template v-if="nouveauPrix > 0">
        <!-- Il existe une promotion : on met en gras et on raye l'ancien prix -->
        <p class="font-bold text-decoration-line-through">{{ produit.prix }}€</p>
        <!-- On met en gras et en rouge le nouveau prix de la promotion -->
        <p class="font-bold text-red-600 text-3xl">{{ nouveauPrix }}€</p>
      </template>
      <template v-else>
        <!-- Il n'y a pas de promotion associée, on met en gras l'ancien prix-->
        <p class="font-bold text-2xl ">{{ produit.prix }}€ </p>
      </template>
    </template>
  </div>
  <br>
  <!-- permet de cerntrer -->
  <div class="text-center" v-if="produit.pageGrandParent === 'EspaceAdministrateur'">

    <!-- vérifie si la valeur est faux -->
    <!-- Vrai = affichage en cours des champs d'ajout de promotion. Faux = Non affichage des champs d'ajout de promotion -->
    <template v-if="!appliquerPromo">
      <!-- permet de gérer le stuyle du bouton, et lorsque l'on clique on active la promotion -->
      <button style="border: 1px; padding: 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="activerPromo">Promotion
      </button>
    </template>

    <template v-else>
      <Promotion :idProduit="produit.idProduit" :ancien-prix="produit.prix" @fin-ajout-promotion="validerPromo" ></Promotion>
    </template>
  </div>
</template>

<script setup>

// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';
import * as $filters from "../filters/index.js";
import {onMounted, ref} from "vue";
import store from "../store/index.js";
import {accesEspaceAdmin, getPromotionAssocie} from "../store/mutations.js";
import Service from "../services/index.js";
import Promotion from "./Promotion.vue";

const appliquerPromo = ref(false);

const produit = defineProps({
  idProduit: Number,
  libelle: String,
  description: String,
  categorie: String,
  prix: Number,
  nouveauprix: Number,
  // Si connexionBack vrai, image est String, sinon c'est ImageData.
  image: (store.state.connexionBack) ? String: ImageData,
  pageGrandParent: String,
  idPromotionAssocie: Number
})

// Si connexionBack vrai, pour chaque produit ajoute les propriétés d'une image en début de chaine de caractère de l'image.
const imageReconstruit = ref("data:image/jpeg;base64," + produit.image);

const saisiNouveauPrix = ref(0);

const nouveauPrix = ref(0);

function activerPromo() {
  accesEspaceAdmin();
  appliquerPromo.value = true;
}

function validerPromo() {
  alert("Promotion validée !");
  appliquerPromo.value = false;
}

/**
 * A l'initialisation du composant ProduitElement, si le produit a une promotion assoicée, récupère le nouveau prix
 * relatif à cette promotion.
 */
onMounted(() => {
  if (produit.idPromotionAssocie !== null) {
    console.log(produit.idPromotionAssocie);
    nouveauPrix.value = getPromotionAssocie(produit.idPromotionAssocie);
  }
})

</script>
