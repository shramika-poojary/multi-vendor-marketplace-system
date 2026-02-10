import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { addProduct, uploadProductImage } from "../services/productService";

const AddProduct = () => {
  const { storeId } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    productName: "",
    productDescription: "",
    price: "",
    stock: "",
    imageURL: "",
  });

  const [imageFile, setImageFile] = useState(null);

  if (!storeId) {
    return <p className="text-danger">Store not selected</p>;
  }

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e) => {
    setImageFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      
      let imageUrl = "";
      if (imageFile) {
        const res = await uploadProductImage(imageFile);
        imageUrl = res.data;
      }

      
      await addProduct(storeId, {
        ...product,
        imageURL: imageUrl,
      });

      alert("Product added successfully");
      navigate("/seller-dashboard");

    } catch (err) {
      console.error(err);
      alert("Error adding product");
    }
  };

  return (
    <div className="container mt-4">
      <h3>📦 Add Product</h3>

      <form className="card p-4 shadow" onSubmit={handleSubmit}>
        <input
          className="form-control mb-2"
          name="productName"
          placeholder="Product Name"
          onChange={handleChange}
          required
        />

        <textarea
          className="form-control mb-2"
          name="productDescription"
          placeholder="Description"
          onChange={handleChange}
          required
        />

        <input
          className="form-control mb-2"
          name="price"
          type="number"
          placeholder="Price"
          onChange={handleChange}
          required
        />

        <input
          className="form-control mb-2"
          name="stock"
          type="number"
          placeholder="Stock"
          onChange={handleChange}
          required
        />

        <input
          type="file"
          className="form-control mb-3"
          onChange={handleImageChange}
          required
        />

        <button className="btn btn-success w-100">
          Add Product
        </button>
      </form>
    </div>
  );
};

export default AddProduct;
