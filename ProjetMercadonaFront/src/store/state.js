
export default {
  // Prévient toutes les pages de la connexion adminitrateur.
  connexionAdmin: localStorage.getItem("connexionAdmin") || false,
  // Active ou pas la connexion avec le back. true = connecté au back, false = non connecté au back.
  connexionBack: localStorage.getItem("connexionBack") || false,
  // Répertorie tous les produits.
  catalogue: localStorage.getItem("catalogue") || [],
  // Répertorie toutes les promotions.
  promotions: localStorage.getItem("promotions") || []
}
