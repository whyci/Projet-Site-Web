import {createApp, ref} from "vue";
import router from "./router";
import "./style.css";
import App from "./App.vue";
import * as filters from './filters'
import store from "./store/index.js";
import Service from "./services/index.js";

const app = createApp(App);
app.use(router).use(store).mount("#app");

app.config.globalProperties.$filters = filters

/* - - - - - Ci-dessous s'enchainent les actions de l'application au démarrage du site web. - - - - - */

// Paramètre la valeur connexionBack dans toute l'application. Elle permet de savoir si on veut connecter le front
// avec le back. true = connecté au back, false = non connecté au back.

