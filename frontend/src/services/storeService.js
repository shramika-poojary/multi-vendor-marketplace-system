import axios from "axios";

const BASE_URL = "http://localhost:8080";

const authHeader = () => ({
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`,
  },
});

export const addStore = (storeData) => {
  return axios.post(`${BASE_URL}/api/store/stores`, storeData, authHeader());
};

export const getMyStores = () => {
  return axios.get(`${BASE_URL}/api/store/get/stores/vendor`, authHeader());
};

export const getAllStores = () => {
  return axios.get(`${BASE_URL}/api/store/stores`);
};