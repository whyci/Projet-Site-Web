<template>
  <div class="submit-form">
    <div class="form-group">
      <label for="pourcentageRemise">Pourcentage de remise</label>
      <input
        type="text"
        class="form-control"
        id="pourcentageRemise"
        required
        v-model="pourcentageRemise"
        name="pourcentageRemise"
      />
    </div>
  </div>
  <button style="border : 1px solid #000; padding : 5px" class="btn btn-success" @click="applicationRemise()">Appliquer promotion</button>
  <br><br/>

  <h4 class="font-semibold">Produit :</h4>
  <p></p>id : {{ produit.id }}<p></p>ancien prix : {{ produit.ancienPrix }}
  <br><br/>
  <h4 class="font-semibold">Promotion :</h4>
  <p></p>pourcentage : {{ pourcentageRemise }}<p></p>nouveau prix : {{ nouveauPrix }}<p></p>
</template>

<script setup>

import {ref} from "vue";

const produit = defineProps({
  id: Number,
  ancienPrix: Number
})

const nouveauPrix = ref(0.0);
const pourcentageRemise = ref(0);

function calculNouveauPrix() {
  if (pourcentageRemise.value > 100) {pourcentageRemise.value = 100;}
  else if (pourcentageRemise.value < 0) {pourcentageRemise.value = 0;}
  nouveauPrix.value = parseFloat((produit.ancienPrix*(1-(pourcentageRemise.value/100))).toFixed(2));
}

/**
 * Applique la promotion (changement de prix) au produit en envoyant la requete d'ajout d'une promotion à la base de
 * donnée. Applique aussi la requete d'association du produit et de la promotion.
 */
function applicationRemise() {
  // Appelle du calcul du nouveau prix.
  calculNouveauPrix();
}

</script>

