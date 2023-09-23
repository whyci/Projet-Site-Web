<template>
  <navbar />
  <main>
    <div class="w-full">
      <router-view />
    </div>
  </main>
</template>

<script setup>
import Navbar from "../components/Navbar.vue";
</script>
