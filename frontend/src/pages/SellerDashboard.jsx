import Sidebar from "../components/Sidebar";
import DashboardStats from "../components/DashboardStats";
import ProductList from "../components/ProductList";
import OrdersList from "../components/OrdersList";

const SellerDashboard = () => {
  const stats = [
    { title: "Total Sales", value: "₹1,85,500" },
    { title: "Total Orders", value: 78 },
    { title: "Active Listings", value: 12 }
  ];

  const products = [
    { name: "Ceramic Mug", price: 299, stock: 20, image: "/img/mug.png" },
    { name: "Macrame Wall Art", price: 499, stock: 10, image: "/img/macrame.png" },
  ];

  const orders = [
    { id: "101", customer: "Alice", total: 1299 },
    { id: "102", customer: "Bob", total: 499 }
  ];

  return (
    <div className="d-flex">
      <Sidebar />
      <div className="p-4 flex-fill">
        <h3>Hello, Seller!</h3>
        <DashboardStats stats={stats} />
        <ProductList products={products} />
        <OrdersList orders={orders} />
      </div>
    </div>
  );
};

export default SellerDashboard;
