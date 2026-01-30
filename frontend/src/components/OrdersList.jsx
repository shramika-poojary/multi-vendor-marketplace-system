const OrdersList = ({ orders }) => {
    

  return (
    <div className="orders-list mt-4">
      <h5>Recent Orders</h5>
      {orders.map((order, idx) => (
        <div key={idx} className="card p-2 mb-2 shadow-sm">
          <p><strong>Order ID:</strong> {order.id}</p>
          <p><strong>Customer:</strong> {order.customer}</p>
          <p><strong>Total:</strong> ₹{order.total}</p>
        </div>
      ))}
    </div>
  );
};

export default OrdersList;
