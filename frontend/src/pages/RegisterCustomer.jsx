import { useState } from "react";
import "../styles/RegisterCustomer.css";
import { registerUser } from "../services/authService";
function RegisterCustomer() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    contact: ""
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit =async (e) => {
    e.preventDefault();
    try {
      await registerUser(formData);
      alert("registered successfully!");
    } catch (error) {
      alert("Registration failed");
    }
  };

  return (
    <div className="register-page d-flex align-items-center justify-content-center">
      <div className="register-card shadow">

        {/* Header */}
        <div className="register-header text-center">
          <h2>Create Account</h2>
          <p>Register as a customer to explore small businesses</p>
        </div>

        {/* Form */}
        <form className="register-form" onSubmit={handleSubmit}>

          <div className="row mb-3">
            <div className="col">
              <input
                type="text"
                name="firstName"
                className="form-control"
                placeholder="First Name"
                value={formData.firstName}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col">
              <input
                type="text"
                name="lastName"
                className="form-control"
                placeholder="Last Name"
                value={formData.lastName}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="mb-3">
            <input
              type="email"
              name="email"
              className="form-control"
              placeholder="Email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <input
              type="password"
              name="password"
              className="form-control"
              placeholder="Password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <input
              type="text"
              name="contact"
              className="form-control"
              placeholder="Contact Number"
              value={formData.contact}
              onChange={handleChange}
              required
            />
          </div>

          <button type="submit" className="btn btn-primary w-100">
            Register
          </button>
        </form>

        <div className="register-footer text-center mt-3">
          <p>
            Already have an account? <span className="login-link">Login</span>
          </p>
        </div>

      </div>
    </div>
  );
}

export default RegisterCustomer;
