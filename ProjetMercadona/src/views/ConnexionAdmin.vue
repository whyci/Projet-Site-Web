<template>
  <div class="p-10">
    <h1 class="text-4xl font-bold text-green-900">Se connecter Admin</h1>
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <br>
  <div>
  <p>Veuillez rentrer vos informations pour vous connecter.</p>
  <br> <br>
  </div>
    <div class="text-center">
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-50"
    placeholder="Adresse mail *"
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
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-50"
    placeholder="Mot de passe *"
  />
<br>
  <input
    type="text"
    v-model="keyword"
    class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-50"
    placeholder="Numéro identifiant admin *"
  />
    <div class="text-center p-2">
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="">Valider</button>
      <br><br>
    </div>
  </div>
  </div>
    <footer class="footer-bg">
    </footer>
  <br>
  <div class="text-center p-2">
    <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
            class="btn btn-success" @click="">Pas encore de compte ? Clique ici</button>
    <br><br>
  </div>
</template>
<script setup>
</script>