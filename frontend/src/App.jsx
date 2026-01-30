import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Stores from "./pages/Stores";
import About from "./pages/About";
import Footer from "./components/Footer";
import ProfileOffcanvas from "./components/ProfileOffcanvas";
import Login from "./pages/Login";
import RegisterCustomer from "./pages/RegisterCustomer";
import RegisterSeller from "./pages/RegisterSeller";
import SellerDashboard from "./pages/SellerDashboard";
import AddStore from "./pages/AddStore";
import SelectStore from "./pages/SelectStore";
import AddProduct from "./pages/AddProduct";
import StoreDetails from "./pages/storeDetails";
import Cart from "./pages/Cart";


function App(){
  return(<>

  <Navbar/>
  <ProfileOffcanvas/>
    <Routes>
      <Route path="/" element={<Home/>}/>
       <Route path="/login" element={<Login/>} />
       <Route path="/register-customer" element={<RegisterCustomer/>} />
       <Route path="/register-seller" element={<RegisterSeller/>} />
      <Route path="/stores" element={<Stores/>}/>
      <Route path="/about" element={<About/>}/>
      <Route path="/seller-dashboard" element={<SellerDashboard/>} />
      <Route path="/add-store" element={<AddStore/>} />
      <Route path="/select-store" element={<SelectStore/>} />
      <Route path="/add-product/:storeId" element={<AddProduct/>} />
      <Route path="/stores/:id" element={<StoreDetails/>} />
      <Route path="/cart" element={<Cart/>}></Route>
    </Routes>
    <Footer/>

  </>
  )
}

export default App;