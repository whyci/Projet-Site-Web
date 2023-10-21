<template>
  <div class="p-3 bg-white shadow rounded-xl hover:scale-105 transition-all h-96">
    <div class="">
      <!-- permet de gérer l'appel de l'image et son affichage -->
       <template v-if="store.state.connexionBack">
        <img v-if="imageReconstruit !== null" :src="imageReconstruit" alt="Image" />
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
    <!-- permet de mettre en gras et rayer le prix s'il y a une promotion -->
    <p v-if="produit.nouveauprix" class="font-bold text-decoration-line-through">{{ produit.prix }}€ </p>
    <!-- permet de mettre en gras et gérer la taille du texte s'il n'y a pas de promotion -->
    <p v-else class="font-bold text-2xl ">{{ produit.prix }}€ </p>

    <!-- permet de mettre en gras, mettre en rouge et gérer la taille du texte de la promotion -->
    <p v-if="produit.nouveauprix >0" class="font-bold text-red-600 text-3xl">{{ produit.nouveauprix }}€</p>
    <p v-else-if="saisiNouveauPrix > 0" class="font-bold text-red-600 text-3xl">{{ saisiNouveauPrix }}€</p>
  </div>
  <br>
  <!-- permet de cerntrer -->
    <div class="text-center" v-if="produit.pageGrandParent === 'EspaceAdministrateur'">

      <!-- vérifie si la valeur est faux -->
      <!-- Vrai = affichage en cours des champs d'ajout de promotion. Faux = Non affichage des champs d'ajout de promotion -->
      <template v-if="!appliquerPromo">
        <!-- permet de gérer le stuyle du bouton, et lorsque l'on clique on active la promotion -->
      <button style="border: 1px; padding: 12px; background-color : lightgray; color :black"
            class="btn btn-success" @click="activerPromo">Promotion</button>
      </template>

      <template v-else>
        <!-- permet de creer des espaces permettant d'écrire le nouveau prix, et de gérer le style de l'input-->
        <input
          type="text"
          @change="enSaisiPromo"
          v-model="pourcentageRemise"
          class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 "
          placeholder="Ecrire la promotion en % *"
        />
        <br>
        <p class="font-bold text-2xl ">{{ saisiNouveauPrix }}</p>
        <button style="border: 1px; padding: 12px; background-color : lightgray; color :black"
                class="btn btn-success" @click="validerPromo">Valider Promotion</button>
      </template>
    </div>
</template>

<script setup>

// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';
import * as $filters from "../filters/index.js";
import {ref} from "vue";
import store from "../store/index.js";
import {accesEspaceAdmin} from "../store/mutations.js";

const appliquerPromo = ref(false);

const produit = defineProps({
  libelle: String,
  description: String,
  categorie: String,
  prix: Number,
  nouveauprix: Number,
  // Si connexionBack vrai, image est String, sinon c'est ImageData.
  image: (store.state.connexionBack) ? String: ImageData,
  pageGrandParent: String,
})

// Si connexionBack vrai, pour chaque produit ajoute les propriétés d'une image en début de chaine de caractère de l'image.
const imageReconstruit = ref("data:image/jpeg;base64," + produit.image);

const pourcentageRemise = ref(0);

const saisiNouveauPrix = ref(0);

function enSaisiPromo() {
  saisiNouveauPrix.value = (1-(pourcentageRemise.value / 100)) * produit.prix;
}
function activerPromo() {
  accesEspaceAdmin();
  appliquerPromo.value = true;
}

function validerPromo() {
  accesEspaceAdmin();
  appliquerPromo.value = false;
}

</script>
