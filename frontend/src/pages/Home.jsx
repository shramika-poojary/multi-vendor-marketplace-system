import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="container-fluid pt-5 mt-4">

      {/* HERO SECTION */}
      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="bg-primary text-white rounded-4 p-5 text-center">
            <h1 className="fw-bold">
              Discover & Support Local Small Businesses
            </h1>
            <p className="mt-3">
              Find unique products from sellers in your community.
            </p>

            <div className="d-flex justify-content-center gap-3 mt-4">
              <Link to="/stores" className="btn btn-light btn-lg">
                Explore Stores
              </Link>
              <Link to="/register-seller" className="btn btn-outline-light btn-lg">
                Join as Seller
              </Link>
            </div>
          </div>
        </div>
      </div>

      {/* FEATURED STORES */}
      <div className="row justify-content-center mt-5">
        <div className="col-md-10">
          <h3 className="fw-semibold mb-4">Featured Stores</h3>

          <div className="row g-4">
            {/* Store Card */}
            <div className="col-md-6 col-lg-3">
              <div className="card shadow-sm h-100">
                <div className="card-body">
                  <div className="mb-3 bg-secondary rounded" style={{ height: "120px" }}></div>
                  <h5 className="card-title">Willow Creek Crafts</h5>
                  <p className="card-text text-muted">
                    Find unique products from sellers and community innovation.
                  </p>
                  <Link to="/stores/1" className="btn btn-primary w-100">
                    Explore Store
                  </Link>
                </div>
              </div>
            </div>

            <div className="col-md-6 col-lg-3">
              <div className="card shadow-sm h-100">
                <div className="card-body">
                  <div className="mb-3 bg-secondary rounded" style={{ height: "120px" }}></div>
                  <h5 className="card-title">Urban Roots Plants</h5>
                  <p className="card-text text-muted">
                    Discover eco-friendly plant collections.
                  </p>
                  <Link to="/stores/2" className="btn btn-primary w-100">
                    Explore Store
                  </Link>
                </div>
              </div>
            </div>

            <div className="col-md-6 col-lg-3">
              <div className="card shadow-sm h-100">
                <div className="card-body">
                  <div className="mb-3 bg-secondary rounded" style={{ height: "120px" }}></div>
                  <h5 className="card-title">Urbansbea Plants</h5>
                  <p className="card-text text-muted">
                    Handmade plant pots and decor.
                  </p>
                  <Link to="/stores/3" className="btn btn-primary w-100">
                    Explore Store
                  </Link>
                </div>
              </div>
            </div>

            <div className="col-md-6 col-lg-3">
              <div className="card shadow-sm h-100">
                <div className="card-body">
                  <div className="mb-3 bg-secondary rounded" style={{ height: "120px" }}></div>
                  <h5 className="card-title">Edwards Plants</h5>
                  <p className="card-text text-muted">
                    Sustainable gardening essentials.
                  </p>
                  <Link to="/stores/4" className="btn btn-primary w-100">
                    Explore Store
                  </Link>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>

    </div>
  );
}

export default Home;
