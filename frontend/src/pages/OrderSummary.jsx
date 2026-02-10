import { useEffect, useState } from "react";
import { getCart } from "../services/cartService";
import { useNavigate } from "react-router-dom";
import { placeOrder } from "../services/orderService";
function OrderSummary() {
  const [cart, setCart] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    getCart().then(res => setCart(res.data));
  }, []);

  if (!cart) return <p>Loading...</p>;

  return (
    <div className="container mt-4">
      <h3>Order Summary</h3>

      {cart.items.map(item => (
        <div key={item.cartItemId}>
          {item.productName} × {item.quantity} = ₹{item.subTotal}
        </div>
      ))}

      <h4>Total: ₹{cart.totalAmount}</h4>

      <button
        className="btn btn-primary mt-3"
        onClick={() => {
            placeOrder().then((res)=> {
            console.log("Order created:", res.data);
            navigate("/payment", { state: { order: res.data } });
            
            
            })
        }
        
        }
      >
        Continue
      </button>
    </div>
  );
}

export default OrderSummary;
