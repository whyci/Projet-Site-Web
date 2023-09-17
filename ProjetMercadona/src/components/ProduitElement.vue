<template>

  <div class="p-3 bg-white shadow rounded-xl hover:scale-105 transition-all h-100">
    <div class="">
    <img class="rounded-xl border-8 border-white ml-auto h-40 mr-auto" v-if="produit.image" :src="image" alt="Select image"></div>
      <div>
      </div>
        <br>
        <p class="font-bold text-xl-center text-2xl">{{ produit.libelle }}</p>
        <p class="font-semibold text-xl-center">Categorie : {{ produit.categorie }}</p>
        <p class="mb-2">
          {{ $filters.truncateWords( produit.description , 20) }}
        </p>
        <p v-if="produit.nouveauprix" class="font-bold text-decoration-line-through">{{ produit.prix }}€ </p>
        <p v-else class="font-bold text-2xl ">{{ produit.prix }}€ </p>

        <p v-if="produit.nouveauprix >0 " class="font-bold text-red-600 text-3xl">{{ produit.nouveauprix }}€</p>
    </div>
</template>

<script setup>
// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';


const produit = defineProps({
  libelle: String,
  description: String,
  categorie: String,
  prix: Number,
  nouveauprix:Number,
  image: ImageData
})

</script>