import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg bg-white shadow-sm sticky-top">
      <div className="container">

        {/* LOGO */}
        <Link className="navbar-brand fw-bold" to="/">
          ShopCircle
        </Link>

        {/* TOGGLER */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#mainNavbar"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* NAV ITEMS */}
        <div className="collapse navbar-collapse" id="mainNavbar">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link active" to="/">Home</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/stores">Stores</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/about">About</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">Contact</Link>
            </li>
          </ul>

          {/* SEARCH BAR */}
          <form className="d-flex me-3">
            <input
              className="form-control form-control-sm me-2"
              type="search"
              placeholder="Search"
            />
            <button className="btn btn-primary btn-sm" type="submit">
              🔍
            </button>
          </form>

          {/* ICONS */}
          <div className="d-flex align-items-center gap-3">
            <Link to="/cart" className="text-dark fs-5">
              🛒
            </Link>
             <button
    className="btn p-0 border-0 bg-transparent fs-5"
    data-bs-toggle="offcanvas"
    data-bs-target="#profileOffcanvas"
    aria-controls="profileOffcanvas"
  >
    👤
  </button>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
