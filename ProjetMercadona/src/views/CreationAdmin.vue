<template>
  <div class="p-10">
    <h1 class="text-4xl font-bold text-green-900">Se créer un compte Administrateur</h1>
      <br>
  <p>Veuillez remplir le formulaire ci-dessous afin de vous créer un compte administrateur les informations comportants un signe * sont obligatoire.</p>
 <br> <br>
    <div class="text-center">

      <select v-model="selected" class="border-gray-200 rounded border-2 focus:border-green-800 mb-4 w-25">
        <option disabled value=""> Choisissez votre civilité </option>
        <option>Madame</option>
        <option>Monsieur</option>
        <option>Autre</option>

      </select>

    <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20"
    placeholder="Nom *"
  />
    <br>
      <!-- rounded border-2 permet d'arrondir légérement les angles, bg-white de mettre le fond en blanc
  border-gray-200 de griser la bordure de base du carré
  focus:border-green-800 de rendre le bordure verte quand le carré est sélectionné
  mb-4 de créer des marges autour de la barre et w-full de générer la barre sur toute la longueur de la fenêtre-->

      <!-- placeholder permet d'écrire dans la barre ce que l'utilisateur devra remplir -->
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
    placeholder="Prénom *"
  />
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20"
    placeholder="Date de naissance *"
  />
    <br>
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
    placeholder="Numéro identifiant admin *"
  />
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20"
    placeholder="Adresse mail *"
  />
    <br>
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
    placeholder="Téléphone *"
  />
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-3 w-25 ml-20"
    placeholder="Mot de passe *"
  />
  <br>
      <div class="p-2 text-center">
        <br>
        <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
                class="btn btn-success" @click="">Valider</button>
      </div>
    </div>
  </div>
  <footer class="footer-bg">
  </footer>
  <div class="text-center p-2">
    <br>
    <router-link
      :to="{ name: 'connexionAdmin' }"
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
</script>