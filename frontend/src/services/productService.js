import axios from "axios";

const BASE_URL = "http://localhost:8080";

const authHeader = () => ({
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`,
  },
});

export const addProduct = (storeId, productData) => {
  return axios.post(
    `${BASE_URL}/api/product/add/products/${storeId}`,
    productData,
    authHeader()
  );
};

 
  export const getProductsByStore = (storeId) => {
    console.log("In Fetching method");
  return axios.get(
    `${BASE_URL}/api/product/store/${storeId}`
  );
};

export const uploadProductImage = (file) => {
  const formData = new FormData();
  formData.append("file", file);

  return axios.post(
    `${BASE_URL}/api/product/upload`,
    formData,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        
      },
    }
  );
};
