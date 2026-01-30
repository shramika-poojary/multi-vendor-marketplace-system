import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { getMyStores } from "../services/storeService";
const SelectStore=()=>{
    const [stores,setStores]=useState([]);
    const navigate=useNavigate();

    useEffect(()=>{
        getMyStores()
        .then(res=>setStores(res.data))
        .catch(err=>console.log(err));
    },[]);

    return(
         <div className="container mt-4">
      <h3>Select Store</h3>

      {stores.map(store => (
        <div key={store.storeId} className="card p-3 mb-2">
          <h5>{store.storeName}</h5>
          <p>{store.city}</p>

          <button
            className="btn btn-primary"
            onClick={() => navigate(`/add-product/${store.storeId}`)}
          >
            Select Store
          </button>
        </div>
      ))}
    </div>


    );
}

export default SelectStore;