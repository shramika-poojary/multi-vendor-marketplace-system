import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const placeOrder = () => {
  const token = localStorage.getItem("token");

  return axios.post(
    `${BASE_URL}/api/orders/place/order`,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
};

export const getOrderHistory=()=>{
  const token = localStorage.getItem("token");
  return axios.get(
    `${BASE_URL}/api/orders/get/orders`,{
      headers:{
        Authorization:`Bearer ${token}`,
      }
    }
  )
}