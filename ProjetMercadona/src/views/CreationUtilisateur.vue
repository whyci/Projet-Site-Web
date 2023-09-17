<template>
  <div class="p-10">
    <h1 class="text-4xl font-bold text-green-900">Se créer un compte</h1>
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <br>
  <p>Veuillez remplir le formulaire ci-dessous afin de vous créer un compte les informations comportants un signe * sont obligatoire.
     <br>L'adresse mail ou le numéro de téléphonne est obligatoire</p>
    <br>
    <div class="flex flex-wrap justify-center gap-3 px-8 mb-6">
    <router-link
      :to="{ name: 'byLetter', params: { letter } }"
      v-for="letter of letters"
      :key="letter"
      class="w-2 h-2 flex items-center justify-center hover:text-green-800 hover:scale-150 transition-all"
    >
      {{ letter }}
    </router-link>
  </div>
    <div class="text-center">
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-25"
    placeholder="Civilité *"
  />
  <!-- rounded border-2 permet d'arrondir légérement les angles, bg-white de mettre le fond en blanc
    border-gray-200 de griser la bordure de base du carré
    focus:border-green-800 de rendre le bordure verte quand le carré est sélectionné
    mb-4 de créer des marges autour de la barre et w-full de générer la barre sur toute la longueur de la fenêtre-->

  <!-- placeholder permet d'écrire dans la barre ce que l'utilisateur devra remplir -->
<br>
    <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-25 "
    placeholder="Nom *"
  />

  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-25 ml-20 "
    placeholder="Prénom *"
  />
      <br>
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-25 "
    placeholder="Date de naissance *"
  />

    <input
      type="text"
      v-model="keyword"
      class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-25 ml-20"
      placeholder="Téléphone *"
    />
<br>
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-25 "
    placeholder="Adresse mail *"
  />

  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-3 w-25 ml-20"
    placeholder="Mot de passe *"
  />
    </div>
    <div class="p-2 text-center">
      <br>
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="valider">Valider</button>
    </div>
  </div>
    <br>
    <footer class="footer-bg">
    </footer>
    <div class="text-center p-2">
      <br>
      <router-link
        :to="{ name: 'connexionUtilisateur' }"
      >
        <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
                @click="navigate"
                role="link"
        >
          Déjà un compte ? Clique ici
        </button>
      </router-link>
      <br><br>
    </div>
</template>
<script setup>
import 'bootstrap/dist/css/bootstrap.min.css';
import {useRouter} from "vue-router";

const router = useRouter();
// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document

function valider() {
  // Vérifie si les champs sont bien remplis !
  router.push('/connexion-utilisateur');
}
</script>