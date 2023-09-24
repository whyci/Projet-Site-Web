import axiosClient from '../axiosClient.js';

class Service {
  afficherCatalogue() {
    return axiosClient.get("/catalogue/complet");
  }

  chercherProduit(rechercheCategorie) {
    console.log("Recherche catégorie " + rechercheCategorie);
    return axiosClient.get("/catalogue/filtre/categorie/"+rechercheCategorie);
  }

  ajouterProduit(produit) {
    console.log("produit: " + produit.libelle+", cat: "+produit.categorie+", desc: "+produit.description+", prix: "+produit.prix);
    return axiosClient.post("/produit/ajouter", produit);
  }
}

export default new Service();