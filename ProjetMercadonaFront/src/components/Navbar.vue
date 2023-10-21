<template>
  <header class="header-bg">
    <div class="container d-flex justify-content-center position-fixed top-3">
      <div class="">
        <div class="text-center">
          <!-- la fonction col-lg permet d'ajuster la taille de la barre de navigation au texte -->
          <nav
            class="navbar">
            <!-- la fonction top-5 permet d'ajouter un espace au-dessus de la barre de navigation -->

            <header class=" rounded-2 bg-gray-200 h-16 flex">
              <!-- bg-white permet que le fond de la barre soit blanche, h-16 de gérer la hauteur de la barre
                et flex permet d'adapter le texte pour qu'il s'aligne droit dans la barre-->

              <router-link
                :to="{ name: 'accueil' }"
                class="inline-flex items-center px-5 text-green-900 font-bold text-2xl"
              >
                <!-- router-link est le lien qui permet le changement de pages
                  inline-flex permet de centrer le texte, et les items de la barre de navigation
                  px-5 permet de gérer la marge autour d'Accueil, text-green-800 gérer la couleur du texte
                  font-bold permet de mettre le texte en gras et pour gérer la taille du texte : text-2xl -->

                Accueil
              </router-link>

              <!-- Right-aligned button links -->
              <div class="">
                <router-link
                  :to="{ name: 'catalogue' }"
                  class="inline-flex items-center px-3 h-full hover:bg-green-800 hover:text-white text-gray-700"
                >
                  <!-- router-link est le lien qui permet le changement de pages
                 inline-flex permet de centrer le texte, et les items de la barre de navigation
                 px-3 permet de gérer la marge autour de Catalogue, h-full permet d'étendre la couleur verte
                 sur toute la barre de navigation sur la partie conceré par catalogue
                 hover:bg-green-800 gérer la couleur du texte quand on passe la souris dessus
                 hover:text-white permet de mettre le texte en gras et de base le texte est noir -->

                  Catalogue
                </router-link>
                <!--
                <router-link
                  :to="{ name: 'creationUtilisateur' }"
                  class="inline-flex items-center px-3 h-full hover:bg-green-800 hover:text-white text-gray-700"
                >
                  Se créer un compte User
                </router-link>

                <router-link
                  :to="{ name: 'connexionUtilisateur' }"
                  class="inline-flex items-center px-3 h-full hover:bg-green-800 hover:text-white text-gray-700"
                >
                  Se connecter Utilisateur
                </router-link>

                <router-link
                  :to="{ name: 'creationAdmin' }"
                  class="inline-flex items-center px-3 h-full hover:bg-green-800 hover:text-white text-gray-700"
                >
                  Se créer un compte Admin
                </router-link>
                -->
                <!-- v-if vérifie que "etatDeConnexion est faux -->
                <router-link v-if="!etatConnexion"
                  :to="{ name: 'connexionAdmin' }"
                  class="inline-flex items-center px-3 h-full hover:bg-green-800 hover:text-white text-gray-700"
                >
                  Se connecter Admin
                </router-link>

                <router-link
                  :to="{ name: 'espaceAdmin' }"
                  class="inline-flex items-center px-3 h-full hover:bg-green-800 hover:text-white text-gray-700"
                >
                  Espace administrateur
                </router-link>

                <!-- condition pour vour si "etatDeConnexion est de vrai -->
                <button v-if="etatConnexion" style="border:none; margin-bottom:5px "
                  class="btn btn-success inline-flex items-center h-full bg-gray-200 px-3 hover:bg-green-800 hover:text-white text-gray-700"
                        @click="deconnexionAdmin()">Se déconnecter</button>
              </div>
              <!-- End of right-aligned button links -->

            </header>
          </nav>
        </div>
      </div>
    </div>
    <br> <br>  <br> <br>  <br> <br>
<h1 class="text-center text-white font-bold text-4xl">Bienvenue chez Mercadona !</h1>
  </header>
</template>

<script setup>
// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document //
import 'bootstrap/dist/css/bootstrap.min.css';
import {ref} from "vue";
import {deconnexionAdmin, getEtatConnexion} from "../store/mutations.js";

const etatConnexion = ref(localStorage.getItem('token') !== 'null');

const props = defineProps({
  action: {
    type: Object,
    route: String,
    color: String,
    label: String,
  },
  transparent: {
    type: Boolean,
    default: false
  },
  light: {
    type: Boolean,
    default: false
  },
  dark: {
    type: Boolean,
    default: true
  },
  sticky: {
    type: Boolean,
    default: false
  },
  darkText: {
    type: Boolean,
    default: false
  }
});
</script>

<style scoped>
/* CSS for the background image */
.header-bg{
  background-image: url('/nat.png'); /* Adjust the path accordingly */
  background-position: center;
  background-repeat: repeat;
  min-height:300px; /* Adjust this height as needed */
}
</style>
