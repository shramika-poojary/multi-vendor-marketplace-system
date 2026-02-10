import { Link } from "react-router-dom";
import "../styles/Store.css";

function StoreCard({ store }) {
  return (
    <div className="col-md-4 mb-4">
      <div className="card shadow-sm h-100 border-0 store-card">

        {/* Image */}
        <div className="store-image-wrapper">
          <img
  src={
    store.storeLogo
      ? `http://localhost:8080${store.storeLogo}`
      : "https://via.placeholder.com/300x180?text=Store+Logo"
  }
  alt={store.storeName}
  className="store-image"
/>

        </div>

        {/* Body */}
        <div className="card-body text-center d-flex flex-column">
          <h5 className="card-title fw-bold">
            {store.storeName}
          </h5>

          <p className="text-muted small store-desc">
            {store.storeDescription}
          </p>

          <Link
            to={`/stores/${store.storeId}`}
            className="btn btn-primary btn-sm mt-auto"
          >
            Explore Store
          </Link>
        </div>

      </div>
    </div>
  );
}

export default StoreCard;
