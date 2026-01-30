import { useState } from "react";
import { addStore } from "../services/storeService";
import "../styles/AddStore.css";
const AddStore = () => {
  const [store, setStore] = useState({
    storeName: "",
    storeLogo: "",
    storeDiscription: "",
    aptName: "",
    street: "",
    city: "",
    state: "",
    pincode: "",
    country: "",
  });

  const handleChange = (e) => {
    setStore({ ...store, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addStore(store);
      alert("Store added successfully");
    } catch (err) {
      console.error(err);
      alert("Failed to add store");
    }
  };

  return (
    <div className="container mt-4">
      <h3>🏬 Add Store</h3>

      <form onSubmit={handleSubmit} className="card p-4 shadow">
        <h5>Store Details</h5>
        <input className="form-control mb-2" name="storeName" placeholder="Store Name" onChange={handleChange} />
        <input className="form-control mb-2" name="storeLogo" placeholder="Logo URL" onChange={handleChange} />
        <textarea className="form-control mb-3" name="storeDiscription" placeholder="Description" onChange={handleChange}></textarea>

        <h5>Store Address</h5>
        <input className="form-control mb-2" name="aptName" placeholder="Apartment Name" onChange={handleChange} />
        <input className="form-control mb-2" name="street" placeholder="Street" onChange={handleChange} />
        <input className="form-control mb-2" name="city" placeholder="City" onChange={handleChange} />
        <input className="form-control mb-2" name="state" placeholder="State" onChange={handleChange} />
        <input className="form-control mb-2" name="pincode" placeholder="Pincode" onChange={handleChange} />
        <input className="form-control mb-3" name="country" placeholder="Country" onChange={handleChange} />

        <button className="btn btn-primary w-100">Add Store</button>
      </form>
    </div>
  );
};

export default AddStore;
