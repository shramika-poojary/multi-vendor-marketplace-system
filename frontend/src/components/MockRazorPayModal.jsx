import { useState } from "react";
import { confirmPayment } from "../services/paymentService";
import { useNavigate } from "react-router-dom";

function MockRazorpayModal({ payment, onClose }) {
  const [method, setMethod] = useState("UPI");
  const navigate = useNavigate();

  const handlePay = () => {
    confirmPayment(payment.paymentId).then(() => {
      alert("Payment Successful ✅");
      onClose();
      navigate("/order-success");
    });
  };

  return (
    <div className="modal show d-block" style={{ background: "rgba(0,0,0,0.5)" }}>
      <div className="modal-dialog modal-dialog-centered">
        <div className="modal-content rounded-4">

          {/* Header */}
          <div className="modal-header">
            <h5 className="modal-title fw-bold">Razorpay</h5>
            <button className="btn-close" onClick={onClose}></button>
          </div>

          {/* Body */}
          <div className="modal-body">
            <p className="text-muted mb-2">Paying</p>
            <h4 className="fw-bold mb-3">₹{payment.amount}</h4>

            <div className="list-group">
              <label className="list-group-item">
                <input
                  type="radio"
                  name="payment"
                  checked={method === "UPI"}
                  onChange={() => setMethod("UPI")}
                />{" "}
                UPI (Google Pay / PhonePe)
              </label>

              <label className="list-group-item">
                <input
                  type="radio"
                  name="payment"
                  checked={method === "CARD"}
                  onChange={() => setMethod("CARD")}
                />{" "}
                Credit / Debit Card
              </label>
            </div>
          </div>

          {/* Footer */}
          <div className="modal-footer">
            <button className="btn btn-dark w-100" onClick={handlePay}>
              Pay ₹{payment.amount}
            </button>
          </div>

        </div>
      </div>
    </div>
  );
}

export default MockRazorpayModal;
