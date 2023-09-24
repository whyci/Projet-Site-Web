<template>

  <div class="p-3 bg-white shadow rounded-xl hover:scale-105 transition-all h-96">
    <div class="">
    <img class="rounded-xl border-8 border-white ml-auto h-40 mr-auto" v-if="produit.image" :src="image" alt="Select image"></div>
    <br>
    <p class="font-bold text-xl-center text-2xl">{{ produit.libelle }}</p>
    <p class="font-semibold text-xl-center">Categorie : {{ produit.categorie }}</p>
    <p class="mb-2 text-center">
          {{ $filters.truncateWords( produit.description , 20) }}
    </p>
    <p v-if="produit.nouveauprix" class="font-bold text-decoration-line-through">{{ produit.prix }}€ </p>
    <p v-else class="font-bold text-2xl ">{{ produit.prix }}€ </p>

    <p v-if="produit.nouveauprix >0" class="font-bold text-red-600 text-3xl">{{ produit.nouveauprix }}€</p>
    <p v-else-if="saisiNouveauPrix > 0" class="font-bold text-red-600 text-3xl">{{ saisiNouveauPrix }}€</p>
  </div>
  <br>
    <div class="text-center" v-if="produit.pageGrandParent === 'EspaceAdministrateur'">
      <!-- vérifie si la valeur est faux -->
      <template v-if="!appliquerPromo">
      <button style="border: 1px; padding: 12px; background-color : lightgray; color :black"
            class="btn btn-success" @click="activerPromo">Promotion</button>
      </template>
      <template v-else>
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

const appliquerPromo = ref(false);

const produit = defineProps({
  libelle: String,
  description: String,
  categorie: String,
  prix: Number,
  nouveauprix: Number,
  image: ImageData,
  pageGrandParent: String,
})

const pourcentageRemise = ref(0);

const saisiNouveauPrix = ref(0);

function enSaisiPromo() {
  saisiNouveauPrix.value = (1-(pourcentageRemise.value / 100)) * produit.prix;
}
function activerPromo() {
  appliquerPromo.value = true;
}

function validerPromo() {
  appliquerPromo.value = false;
}

</script>