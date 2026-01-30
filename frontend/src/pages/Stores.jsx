import { useEffect, useState } from "react";
import StoreCard from "../components/StoreCard";
import { getAllStores } from "../services/storeService";

function Stores() {
  const [stores, setStores] = useState([]);
   useEffect(() => {
    getAllStores()
      .then((res) => {
          console.log("Stores API response:", res.data); 
        setStores(res.data);})
      .catch((err) => console.error(err));
  }, [])
  return (
    <div className="container mt-4">
      <h3 className="mb-4">All Local Stores</h3>
      

      <div className="row">
        {stores.map((store)=>(
          <StoreCard key={store.storeId} store={store}/>
        ))}
      </div>
    </div>
  );
}

export default Stores;
