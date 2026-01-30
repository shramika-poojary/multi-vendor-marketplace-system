import { addToCart } from "../services/cartService";
import { useNavigate } from "react-router-dom";

function ProductCard({ product }) {
  const navigate = useNavigate();
  const handleAddToCart=()=>{
    addToCart({
      productId:product.productId,
      quantity:1
    }).then(()=>{
      alert("Item Added to cart");
      navigate("/cart");
    }).catch(err=>{
      console.error(err);
      alert("Failed to add to cart");
    });
  }

  return (
    <div className="col-md-3 mb-4">
      <div className="card h-100 shadow-sm">
        <div className="bg-light" style={{ height: "150px" }}></div>

        <div className="card-body text-center">
          <h4>{product.productName}</h4>
           <h6>{product.productDescription}</h6>
          <p>₹{product.price}</p>

          <button className="btn btn-sm btn-success" onClick={handleAddToCart}>
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
}

export default ProductCard;
