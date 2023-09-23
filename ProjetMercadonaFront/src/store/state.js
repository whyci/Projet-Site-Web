
export default {
  connexionAdmin: localStorage.getItem("connexionAdmin") || null, // Prévient toutes les pages de la connexion adminitrateur.
  // Active ou pas la connexion avec le back. true = connecté au back, false = non connecté au back.
  connexionBack: localStorage.getItem("connexionBack") || null
}
