<template>
 <router-view />
</template>

<script setup>
import { RouterView } from "vue-router";
import { onMounted } from "vue";

onMounted(() => {
  console.log("App started");
})
</script>

<style scoped>
</style>
