<template>
 <router-view />
</template>

<script setup>
import { RouterView } from "vue-router";
import {onMounted, ref} from "vue";
import store from "./store/index.js";
import Service from "./services/index.js";
import {validerMotDePasse, validerNom} from "./control/index.js";

onMounted(() => {
  console.log("App started");
})
</script>

<style scoped>
</style>
