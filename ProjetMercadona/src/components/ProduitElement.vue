<template>
  <div
    class="p-3 bg-white shadow rounded-xl hover:scale-105 transition-all h-100"
  >
    <img class="display-flex rounded-xl border-8 border-white h-48" v-if="produit.image" :src="image" alt="Select image">

      <br>
    <h3 class="font-bold">{{ produit.libelle }}</h3>
    <h4 class="font-semibold">Categorie : {{ produit.categorie }}</h4>
    <p class="mb-2">
      {{ $filters.truncateWords( produit.description , 20) }}
    </p>
    <h2 class="font-bold">{{ produit.prix }}€</h2>
  </div>
</template>

<script setup>
/* Importe la framework Bootstrap 5 qui permet d'améliorer le front du document */
import 'bootstrap/dist/css/bootstrap.min.css';


const produit = defineProps({
  libelle: String,
  description: String,
  categorie: String,
  prix: Number,
  image: ImageData
})

</script>