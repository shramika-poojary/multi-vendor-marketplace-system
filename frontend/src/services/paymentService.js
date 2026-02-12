import axios from "axios";

const BASE_URL = "http://localhost:8080/api/payment";

export const initiatePayment = (orderId, method) => {
  const token = localStorage.getItem("token");

  return axios.post(
    `${BASE_URL}/initiate?orderId=${orderId}&method=${method}`,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
}
export const confirmPayment = (paymentId, success) => {
  const token = localStorage.getItem("token");

  return axios.put(
    `http://localhost:8080/api/payment/confirm/${paymentId}?success=${success}`,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
};

