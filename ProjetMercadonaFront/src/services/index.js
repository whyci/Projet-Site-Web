import axiosInstance from '../axiosClient';

class Index {
  serviceDemanderCatalogue() {
    return axiosInstance.get("/catalogue/complet");
  }

  serviceDemanderPromotions() {
    return axiosInstance.get("/promotion/complet");
  }

  serviceAjouterProduitImage(formulaireDonneeImage, libelle, categorie, desccription, prix) {
    console.log("formulaire : "+formulaireDonneeImage+", image : "+formulaireDonneeImage.get("image"));
    return axiosInstance.post("/produit/ajouter/image", formulaireDonneeImage);
  }

  serviceAjouterProduitParametres(produit, idProduit) {
    console.log("Envoie des paramètres avec id : " + idProduit);
    return axiosInstance.post("/produit/ajouter/produit/"+idProduit, produit);
  }

  serviceAjouterPromotion(promotion, idProduit) {
    return axiosInstance.post("/promotion/admin/ajouter/"+idProduit, promotion);
  }

  serviceInscrireAdministrateur(administrateur) {
    return axiosInstance.post("/administrateur/inscrire", administrateur);
  }

  serviceConnecterAdministrateur(adresseMail, motDePasse) {
    console.log(adresseMail, motDePasse);
    return axiosInstance.get("/administrateur/connecter/"+adresseMail+"/"+motDePasse);
  }

  serviceAccesEspaceAdmin() {
    return axiosInstance.get("/administrateur/acces");
  }
}

export default new Index();
