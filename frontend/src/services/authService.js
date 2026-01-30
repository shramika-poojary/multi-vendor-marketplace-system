import axios from "axios";

const API_URL="http://localhost:8080/api/auth";

//login 
export const loginUser=async (loginData)=>{
    const response = await axios.post(`${API_URL}/login`,loginData);
    console.log(response.data);
    return response.data;
}

//register customer
export const registerUser = async (formData) => {
    const payload = {
    firstName: formData.firstName,
    lastName: formData.lastName,
    email: formData.email,
    password: formData.password,
    contact: formData.contact,
    role: "CUSTOMER"
  };
  return axios.post(`${API_URL}/register`, payload);
};


//register seller
export const registerSeller = async (formData) => {
  const payload = {
    firstName: formData.firstName,
    lastName: formData.lastName,
    email: formData.email,
    password: formData.password,
    contact: formData.contact,
    role: "VENDOR"
  };

  return axios.post(`${API_URL}/register`, payload);
};