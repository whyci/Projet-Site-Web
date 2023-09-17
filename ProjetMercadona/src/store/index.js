import { createStore } from 'vuex'
import state from './state'
import * as mutations from './mutations'

// Crée le store (avec ses state et mutations).
const store = createStore({
  state,
  mutations,
})

// Rend la variable store disponible dans toute l'application.
export default store;