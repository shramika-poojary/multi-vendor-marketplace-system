import { Link } from "react-router-dom";

function StoreCard({ store }) {
  return (
    <div className="col-md-4 mb-4">
      <div className="card shadow-sm h-100">

        {/* Image */}
        <div style={{ height: "180px", overflow: "hidden" }}>
          <img
            src={
              store.storeLogo && store.storeLogo.startsWith("http")
                ? store.storeLogo
                : "https://via.placeholder.com/300x180?text=Store"
            }
            alt={store.storeName}
            className="w-100 h-100"
            style={{ objectFit: "cover" }}
          />
        </div>

        {/* Body */}
        <div className="card-body text-center">
          <h5 className="card-title">{store.storeName}</h5>
          <p className="text-muted small">
            {store.storeDescription}
          </p>

          <Link
            to={`/stores/${store.storeId}`}
            className="btn btn-primary btn-sm"
          >
            Explore Store
          </Link>
        </div>

      </div>
    </div>
  );
}

export default StoreCard;
