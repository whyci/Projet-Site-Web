<template>
  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Se créer un compte Administrateur</h1>
      <br>
  <p>Veuillez remplir le formulaire ci-dessous afin de vous créer un compte administrateur les informations comportants un signe * sont obligatoire.</p>
    <!-- br permet de sauter une ligne -->
    <br> <br>
    <!-- permet de centrer toutes les informations de la page -->
    <div class="text-center">
      <!-- permet de pouvoir sélectionner sa civilité selon les 3 options définies, et le class de design, et le v-model permet de choisir celui de sélection -->
      <select v-model="selected" class="border-gray-200 rounded border-2 focus:border-green-800 mb-4 w-25">
        <option disabled value=""> Choisissez votre civilité </option>
        <option>Madame</option>
        <option>Monsieur</option>
        <option>Autre</option>
      </select>
      <!-- rounded border-2 permet d'arrondir légérement les angles, bg-white de mettre le fond en blanc
         border-gray-200 de griser la bordure de base du carré
         focus:border-green-800 de rendre le bordure verte quand le carré est sélectionné
         mb-4 de créer des marges autour de la barre et w-full de générer la barre sur toute la longueur de la fenêtre-->
      <!-- placeholder permet d'écrire dans la barre ce que l'utilisateur devra remplir -->
    <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20"
    placeholder="Nom *"
  />
    <br>
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
        <!-- permet de gérer la bordure et l'espace du carré pour soumettre la demande
          btn, et btn-success permettent de créer un carré et le mettre en couleur
          les autres informations permettent de rechercher un produit selon sa catégorie
          quand l'on click sur la bouton ça valide le formulaire -->
        <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
                class="btn btn-success" @click="">Valider</button>
      </div>
    </div>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
  <footer class="footer-bg">
  </footer>
  <div class="text-center p-2">
    <br>
    <!-- le router-link permet de faire le lien avec la page de création de connexion admin -->
    <router-link
      :to="{ name: 'connexionAdmin' }"
    >
      <!-- ici on crée le bouton et défini son style - et lorsque l'on clique sur le bouton ça nous amène sur la page de connexion admin -->
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              @click="navigate"
              role="link"
      >
        <!-- le texte du bouton -->
        Déjà un compte ? Clique ici
      </button>
    </router-link>
    <br><br>
  </div>
</template>
<script setup>
</script>