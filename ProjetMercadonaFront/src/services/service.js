import axiosClient from '../axiosClient';

class Index {
  serviceDemanderCatalogue() {
    return axiosClient.get("/catalogue/complet");
  }

  serviceDemanderPromotions() {
    return axiosClient.get("/promotion/complet");
  }

  serviceFiltrerParCategorie(catalogue) {
    return axiosClient.get("/catalogue/filtre/categorie/"+catalogue);
  }

  serviceAjouterProduitImage(formulaireDonneeImage, libelle, categorie, desccription, prix) {
    console.log("formulaire : "+formulaireDonneeImage+", image : "+formulaireDonneeImage.get("image"));
    return axiosClient.post("/produit/ajouter/image", formulaireDonneeImage);
  }

  serviceAjouterProduitParametres(produit, idProduit) {
    console.log("Envoie des paramètres avec id : " + idProduit);
    return axiosClient.post("/produit/ajouter/produit/"+idProduit, produit);
  }

  serviceAjouterPromotion(promotion, idProduit) {
    return axiosClient.post("/promotion/admin/ajouter/"+idProduit, promotion);
  }

  serviceInscrireAdministrateur(administrateur) {
    return axiosClient.post("/administrateur/inscrire", administrateur);
  }

  serviceConnecterAdministrateur(adresseMail, motDePasse) {
    console.log(adresseMail, motDePasse);
    return axiosClient.get("/administrateur/connecter/"+adresseMail+"/"+motDePasse);
  }
}

export default new Index();