import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getProductsByStore } from "../services/productService";
import ProductCard from "../components/ProductCard";

function StoreDetails() {
  const { id } = useParams();
  const [products, setProducts] = useState([]);

 useEffect(() => {
  getProductsByStore(id)
    .then(res => {
      console.log("Products API response:", res.data);
      setProducts(res.data);
    })
    .catch(console.error);
}, [id]);

  return (
    <div className="container mt-4">
      <h3>Store Products</h3>

      <div className="row">
        {products.map(product => (
          <ProductCard key={product.productId} product={product} />
        ))}
      </div>
    </div>
  );
}

export default StoreDetails;
