import axiosClient from '../axiosClient.js';

class Service {
  afficherCatalogue() {
    return axiosClient.get("/catalogue/complet");
  }

  chercherProduit(catalogue) {
    return axiosClient.get("/catalogue/filtre/categorie/"+catalogue);
  }

  ajouterProduit(produit) {
    return axiosClient.post("/produit/ajouter", produit);
  }

  /*
  update(id, data) {
    return http.put(`/tutorials/${id}`, data);
  }

  delete(id) {
    return http.delete(`/tutorials/${id}`);
  }

  deleteAll() {
    return http.delete(`/tutorials`);
  }

  findByTitle(title) {
    return http.get(`/tutorials?title=${title}`);
  }
   */
}

export default new Service();