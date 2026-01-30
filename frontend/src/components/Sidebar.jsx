import { Link } from "react-router-dom";
import "../styles/SellerDashboard.css";

const Sidebar = () => {
  return (
    <div className="sidebar bg-dark text-white p-3">
      <h5>Seller Dashboard</h5>
      <ul className="list-unstyled mt-4">
        <li><Link to="/seller" className="text-white">Dashboard</Link></li>
        <li><Link to="/add-store" className="text-white">➕ Add Store</Link></li>
        <li><Link to="/select-store" className="text-white">📦 Add Products</Link></li>
        <li><Link to="/seller/orders" className="text-white">Orders</Link></li>
        
      </ul>
    </div>
  );
};

export default Sidebar;
