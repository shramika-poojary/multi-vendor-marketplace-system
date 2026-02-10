import { useEffect, useState } from "react";
import { getCart, updateCartItem, removeCartItem } from "../services/cartService";
import { placeOrder } from "../services/orderService";
import { Navigate, useNavigate } from "react-router-dom";
function Cart() {
  const navigate=useNavigate();
  const [cart, setCart] = useState(
    {
    cartId: null,
    items: [],
    totalAmount: 0
    }
  );

  useEffect(() => {
    getCart()
      .then(res => {
        console.log("Cart:", res.data);
        setCart(res.data);
      })
      .catch(err => console.error(err));
  }, []);

   
  return (
    <div className="container mt-4">
      <h3>My Cart</h3>

      {cart.items.map(item => (
        <div key={item.cartItemId} className="card mb-3 p-3">
          <div className="d-flex justify-content-between align-items-center">

            <div>
              <h5>{item.productName}</h5>
              <p>₹{item.price}</p>

              {/* Quantity controls */}
              <div className="d-flex align-items-center">
                <button
                  className="btn btn-sm btn-outline-secondary"
                  disabled={item.quantity <= 1}
                  onClick={() =>
                    updateCartItem(item.cartItemId, item.quantity - 1)
                      .then(res => setCart(res.data))
                  }
                >
                  -
                </button>

                <span className="mx-2">{item.quantity}</span>

                <button
                  className="btn btn-sm btn-outline-secondary"
                  onClick={() =>
                    updateCartItem(item.cartItemId, item.quantity + 1)
                      .then(res => {
                        console.log(res.data);
                        setCart(res.data)})
                  } 
                >
                  +
                </button>

                <button
                  className="btn btn-sm btn-outline-danger ms-3"
                  onClick={() =>
                    removeCartItem(item.cartItemId)
                      .then(res => setCart(res.data))
                  }
                >
                  Remove
                </button>
              </div>

              <p className="mt-2">
                Subtotal: ₹{item.subTotal}
              </p>
            </div>

<img
  src={
     `http://localhost:8080${item.imageURL}`
     
  }
  alt={item.productName}
  className="store-image"
/>
            
          </div>
        </div>
      ))}

      <h4>Total: ₹{cart.totalAmount}</h4>

      <button className="btn btn-success mt-3" onClick={()=>
       navigate("/order-summary")
      }>
        Checkout
      </button>
    </div>
  );
}

export default Cart;
