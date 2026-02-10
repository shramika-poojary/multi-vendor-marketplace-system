import { useEffect, useState } from "react";
import { initiatePayment } from "../services/paymentService";
import MockRazorpayModal from "../components/MockRazorPayModal";
import { useLocation, useNavigate } from "react-router-dom";
function Payment() {
  const [payment, setPayment] = useState(null);
  const [showModal, setShowModal] = useState(false);
 const location = useLocation();
  const navigate = useNavigate();

  const order = location.state?.order; 
  useEffect(() => {
    initiatePayment(order.orderId, "UPI").then(res => {
      console.log("order data sent by order summary"+res.data);
      setPayment(res.data);
    });
  }, []);

  if (!payment) return <p>Preparing payment...</p>;

  return (
    <div className="container mt-5 text-center">
      <h3>Payment</h3>
      <p>Total Amount: ₹{payment.amount}</p>

      <button
        className="btn btn-primary px-5"
        onClick={() => setShowModal(true)}
      >
        Pay with Razorpay
      </button>

      {showModal && (
        <MockRazorpayModal
          payment={payment}
          onClose={() => setShowModal(false)}
        />
      )}
    </div>
  );
}

export default Payment;
