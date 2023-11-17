/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
import axiosInstance from '../axiosClient';

/**
 * Expose les fonctions d'intéractions avec la base de donnée.
 */
export default {
  /**
   * Demande le catalogue des produits au back.
   * @returns AxiosResponse
   */
  serviceDemanderCatalogue() {
    return axiosInstance.get("/catalogue/complet");
  },

  /**
   * Demande toutes les promotions au back.
   * @returns AxiosResponse
   */
  serviceDemanderPromotions() {
    return axiosInstance.get("/promotion/complet");
  },

  /**
   * Ajoute un produit contenant son image. Première partie d'ajout d'un produit.
   * @returns AxiosResponse
   */
  serviceAjouterProduitImage(formulaireDonneeImage) {
    return axiosInstance.post("/produit/ajouter/image", formulaireDonneeImage);
  },

  /**
   * Ajoute les paramètres d'un produit (libellé, prix, etc.). Deuxième partie d'ajout d'un produit.
   * @returns AxiosResponse
   */
  serviceAjouterProduitParametres(produit, idProduit) {
    return axiosInstance.post("/produit/ajouter/produit/"+idProduit, produit);
  },

  /**
   * Ajoute une promotion dans la base de donnée. Identifie le produit associé à la promotion en envoyant son identifiant.
   * @returns AxiosResponse
   */
  serviceAjouterPromotion(promotion, idProduit) {
    return axiosInstance.post("/promotion/admin/ajouter/"+idProduit, promotion);
  },

  /**
   * Inscription d'un administrateur, en l'ajoutant dans la base de donnée.
   * @returns AxiosResponse
   */
  serviceInscrireAdministrateur(administrateur, code) {
    return axiosInstance.post("/administrateur/inscrire/"+code, administrateur);
  },

  /**
   * Connexion d'un administrateur en vérifiant son adresse mail et mdp pour ensuite générer un token en cas de bonne
   * connexion.
   * @returns AxiosResponse
   */
  serviceConnecterAdministrateur(adresseMail, motDePasse) {
    return axiosInstance.get("/administrateur/connecter/"+adresseMail+"/"+motDePasse);
  },

  /**
   * Accès à l'espace admin en vérifiant l'authenticité du token envoyé dans l'entête de la requête.
   * @returns AxiosResponse
   */
  serviceAccesEspaceAdmin() {
    return axiosInstance.get("/administrateur/acces");
  },

  /**
   * Ajoute un code administrateur dans la base de donnée.
   * @returns AxiosResponse
   */
  serviceAjouterCodeAdmin(codeAdmin) {
    return axiosInstance.post("/codeadmin/ajouter/"+codeAdmin);
  }
}
