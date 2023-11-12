/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
import axios from 'axios'

/**
 * Création de l'instance axios qui permet d'envoyer des requêtes au back, et de récupérer les réponses.
 * @type {AxiosInstance} Instance axios créée.
 */
const axiosInstance =  axios.create({
  // Lien URL du destinataire.
  baseURL: "http://localhost:8080",
  // Type de contenu JSON.
  ContentType: "application/json",
  // Entête des requêtes.
  headers: {
    Authorization: "",
  }
});

/**
 * Paramétrage du token dans l'entête des requêtes pour accéder aux ressources de l'espace admin.
 */
axiosInstance.interceptors.request.use( function (config) {
  if (localStorage.getItem("token") !== "null") {
    config.headers.Authorization =  "Bearer "+localStorage.getItem("token");
  }
  return config;
});

export default axiosInstance;
