<!--
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
-->
<template>
  <div className="submit-form">
    <div className="form-group">
      <label htmlFor="pourcentageRemise">Pourcentage de remise</label>
      <!-- permet de creer des espaces permettant d'écrire le nouveau prix, et de gérer le style de l'input-->
      <input
        type="text"
        @change="nouveauPrix = calculNouveauPrix(produit.ancienPrix, pourcentageRemise)"
        v-model="pourcentageRemise"
        class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 "
        placeholder="De 1 à 99"
      /> %
      <br>
      <p class="font-bold text-2xl ">{{ nouveauPrix }}</p>

      <!-- Champs des dates d'effets des promotions -->
      <p style="margin-top:12px; margin-bottom:6px">Date début promotion</p>
      <datepicker style=""
                  class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 "
                  v-model="dateDebut" inputFormat="dd-MM-yyyy"/>

      <p style="margin-top:12px; margin-bottom:6px" >Date fin promotion</p>
      <datepicker style=""
                  class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 "
                  v-model="dateFin" inputFormat="dd-MM-yyyy"/>
    </div>
  </div>
<br>
  <button style="border: 1px; padding: 12px; background-color : lightgray; color :black"
          @click="applicationPromotion">Valider promotion
  </button>
</template>

<script setup>

import {ref} from "vue";
import Service from "../services/index.js";
import Datepicker from 'vue3-datepicker';
import {accesEspaceAdmin, calculNouveauPrix} from "../store/mutations.js";

const produit = defineProps({
  idProduit: Number,
  ancienPrix: Number
})

// Emit pour signaler que la promotion a été validée et créée dans la base de donnée.
const emitFinPromotion = defineEmits(['inFocus', 'submit'])

// Signal le composant parent par un événement que la promotion est terminée.
function signalerFinPromotion() {
  // finAjoutPromotion est l'événement qui déclenchera un callback.
  emitFinPromotion('finAjoutPromotion');
}

const nouveauPrix = ref();
const pourcentageRemise = ref();
const dateDebut = ref(new Date());
const dateFin = ref(new Date());

/**
 * Validation du pourcentage de remise.
 * Valide si le pourcentage de remise est strictement : - supérieur à 0 ; - inférieur à 100.
 * @returns {boolean} Vrai si pourcentage validé, Faux sinon.
 */
function validationPourcentageRemise() {
  return (pourcentageRemise.value > 0) && (pourcentageRemise.value < 100);
}

/**
 * TODO Vérifier commentaire
 * Applique la promotion (changement de prix) au produit en envoyant la requete d'ajout d'une promotion à la base de
 * donnée. Applique aussi la requete d'association du produit et de la promotion.
 */
function applicationPromotion() {

  // Vérifie la validité du token
  accesEspaceAdmin();

  // Vérifie la saisie de l'administrateur (comprise dans les bornes).
  if (!validationPourcentageRemise()) { return;}

  // Entité promotion que l'on souhaite ajouter dans la base de donnée avec les éléments du cahier des charges +
  // id du produit associé.
  let donneePromotion = ref({
    debut: dateDebut.value,
    fin: dateFin.value,
    pourcentageRemise: pourcentageRemise.value,
    produitIdCle: produit.idProduit
  });

  // Envoi de la demande d'ajout à la base de donnée.
  Service.serviceAjouterPromotion(donneePromotion.value, produit.idProduit)
    .then(() => {
      // Si la promotion est finie, on averti le composant parent ProduitElement par un emit.
      signalerFinPromotion();
      location.reload();
    })
    .catch(e => {
      console.log("Erreur détectée : ajout d'une promotion.");
      console.log(e);
    })
}

</script>

