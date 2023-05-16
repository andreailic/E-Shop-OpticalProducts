import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8083/",
  headers: {
    "Content-type": "application/json",
    "Authorization": "Bearer " + localStorage.getItem("Token")
  }
});