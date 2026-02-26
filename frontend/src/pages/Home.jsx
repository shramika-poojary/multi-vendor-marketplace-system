import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import StoreCard from "../components/StoreCard";
import { getAllStores } from "../services/storeService";
import "../styles/Store.css";
function Home() {
  const [stores, setStores] = useState([]);

  useEffect(() => {
    getAllStores()
      .then((res) => {
        console.log("Stores API response:", res.data);
        setStores(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  return (
    <div className="container-fluid pt-5 mt-4">

      {/* HERO SECTION */}
      <div className="row justify-content-center mb-5">
        <div className="col-lg-10">
          <div className="text-white rounded-4 p-5 text-center shadow outer-div">
            <h1 className="fw-bold display-5">
              Discover & Support Local Small Businesses
            </h1>
            <p className="mt-3 fs-5">
              Explore unique stores, shop meaningful products, and empower local entrepreneurs.
            </p>

            <div className="d-flex justify-content-center gap-3 mt-4 flex-wrap">
              <Link to="/stores" className="btn btn-light btn-lg px-4">
                Explore Stores
              </Link>
              <Link
                to="/register-seller"
                className="btn btn-outline-light btn-lg px-4"
              >
                Join as Seller
              </Link>
            </div>
          </div>
        </div>
      </div>

      {/* FEATURED STORES */}
      <div className="row justify-content-center">
        <div className="col-lg-10">
          <div className="d-flex justify-content-between align-items-center mb-4">
            <h3 className="fw-semibold">Featured Stores</h3>
            <Link to="/stores" className="text-decoration-none">
              View All →
            </Link>
          </div>

          <div className="row g-4 flex-nowrap overflow-auto home-featured">
  {stores.length > 0 ? (
    stores.slice(0, 6).map((store) => (
      <div
        className="d-flex mb-3"
        key={store.storeId}
        style={{ flex: "0 0 320px" }}
      >
        <StoreCard store={store} />
      </div>
    ))
  ) : (
    <p className="text-muted">No stores available.</p>
  )}
</div>


        </div>
      </div>
    </div>
  );
}

export default Home;
