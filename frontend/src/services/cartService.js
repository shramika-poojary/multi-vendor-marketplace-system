import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const addToCart=(product)=>{
    return axios.post(`${BASE_URL}/api/cart/items`,product,{
        headers:{
            Authorization:`Bearer ${localStorage.getItem("token")}`
        }
    });
};


export const getCart =()=>{
    return axios.get(`${BASE_URL}/api/cart/get/cart`,{
        headers:{
            Authorization:`Bearer ${localStorage.getItem("token")}`
        }
    })
}

export const updateCartItem = (cartItemId, quantity) => {
  return axios.put(
    `${BASE_URL}/api/cart/items/${cartItemId}`,
    { quantity },
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }
  );
};

export const removeCartItem = (cartItemId) => {
  return axios.delete(
    `${BASE_URL}/api/cart/items/${cartItemId}`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }
  );
};