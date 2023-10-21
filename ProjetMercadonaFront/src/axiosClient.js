import axios from 'axios'
import store from "./store/index.js";

const axiosInstance =  axios.create({
  baseURL: "http://localhost:8080",
  ContentType: "application/json",
  headers: {
    Authorization: "",
  }
});

axiosInstance.interceptors.request.use( function (config) {
  console.log("Passing in the interceptor axios");
  if (localStorage.getItem("token") !== "null") {
    config.headers.Authorization =  "Bearer "+localStorage.getItem("token");
  }
  return config;
});

export default axiosInstance;
