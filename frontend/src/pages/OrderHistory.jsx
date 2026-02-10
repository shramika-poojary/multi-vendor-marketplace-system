import { useEffect, useState } from "react";
import { getOrderHistory } from "../services/orderService";

function OrderHistory() {
  const [orders, setOrders] = useState([]);


  useEffect(() => {
    getOrderHistory()
      .then(res => {
        console.log(res.data);
        setOrders(res.data)})
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="container mt-4">
      <h3 className="mb-4">📦 My Orders</h3>

      {orders.length === 0 && (
        <div className="alert alert-info">
          You haven't placed any orders yet.
        </div>
      )}

      {orders.map(order => (
  <div key={order.orderId} className="card mb-3 shadow-sm">
    <div className="card-body">
      
      <div className="d-flex justify-content-between">
        <div>
          <h6>Order #{order.orderId}</h6>
          <small className="text-muted">
            {new Date(order.orderDate).toLocaleDateString()}
          </small>
        </div>

        <div>
          <span className="badge bg-primary me-2">₹{order.total}</span>
          <span className={`badge ${
            order.orderStatus === "PLACED" ? "bg-success" :
            order.orderStatus === "PENDING" ? "bg-warning" : "bg-danger"
          }`}>
            {order.orderStatus}
          </span>
        </div>
      </div>

      <hr />

      {/* Products */}
      {order.items.map((item, index) => (
        <div key={index} className="d-flex justify-content-between">
          <div className="d-flex align-items-center">
      <img 
        src={item.imageURL} 
        alt={item.productName} 
        style={{ width: "100px", height: "100px", objectFit: "cover", marginRight: "10px", borderRadius: "5px" }}
      />
      
    </div>
          <span>{item.productName} × {item.quantity}</span>
          <span>₹{item.price * item.quantity}</span>
        </div>
      ))}

      <div className="text-end mt-2">
        <span className={`badge ${
          order.paymentStatus === "SUCCESS" ? "bg-success" :
          order.paymentStatus === "PENDING" ? "bg-warning" : "bg-danger"
        }`}>
         
        </span>
      </div>

    </div>
  </div>
))}

    </div>
  );
}

export default OrderHistory;
