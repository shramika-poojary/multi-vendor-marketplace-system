import { Link, useNavigate } from "react-router-dom";
import "../styles/ProfileOffcanvas.css";

const ProfileOffcanvas = () => {
  const role = localStorage.getItem("role");
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div
      className="offcanvas offcanvas-end"
      tabIndex="-1"
      id="profileOffcanvas"
    >
      <div className="offcanvas-header">
        <h5>My Account</h5>
        <button className="btn-close" data-bs-dismiss="offcanvas"></button>
      </div>

      <div className="offcanvas-body">
        <div className="profile-info text-center">
          <i className="bi bi-person-circle fs-1"></i>
          
        </div>

        <ul className="profile-menu">

          {/* ---------- GUEST ---------- */}
          {!role && (
            <li>
              <Link to="/login" className="profile-link">
                <i className="bi bi-box-arrow-in-right me-2"></i>
                Login
              </Link>
            </li>
          )}

          {/* ---------- CUSTOMER ---------- */}
          {role === "ROLE_CUSTOMER" && (
            <>
              
              <li>
                <Link to="/order-history" className="profile-link">
                  <i className="bi bi-bag-check me-2"></i>
                  My Orders
                </Link>
              </li>
            </>
          )}

          {/* ---------- SELLER ---------- */}
          {role === "ROLE_VENDOR" && (
            <li>
              <Link to="/seller-dashboard" className="profile-link">
                <i className="bi bi-speedometer2 me-2"></i>
                Seller Dashboard
              </Link>
            </li>
          )}

          {/* ---------- LOGOUT ---------- */}
          {role && (
            <li onClick={handleLogout} className="text-danger profile-link">
              <i className="bi bi-box-arrow-right me-2"></i>
              Logout
            </li>
          )}
        </ul>
      </div>
    </div>
  );
};

export default ProfileOffcanvas;
