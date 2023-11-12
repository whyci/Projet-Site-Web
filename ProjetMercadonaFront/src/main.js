/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
import {createApp, ref} from "vue";
import router from "./router";
import "./style.css";
import App from "./App.vue";
import * as filters from './filters'
import store from "./store/index.js";

const app = createApp(App);
app.use(router).use(store).mount("#app");

app.config.globalProperties.$filters = filters

