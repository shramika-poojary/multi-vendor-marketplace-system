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
import OrderSummary from "./pages/OrderSummary";
import Payment from "./pages/Payment";
import OrderSuccess from "./pages/OrderSuccess";
import OrderHistory from "./pages/OrderHistory";


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
      <Route path="/order-summary" element={<OrderSummary/>}></Route>
      <Route path="/payment" element={<Payment/>}></Route>
      <Route path="/order-success" element={<OrderSuccess/>} />
      <Route path="/order-history" element={<OrderHistory/>} /> 
    </Routes>
    <Footer/>

  </>
  )
}

export default App;