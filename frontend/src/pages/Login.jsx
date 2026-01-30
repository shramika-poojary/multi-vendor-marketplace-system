import "../styles/Login.css";
import { Link } from "react-router-dom";
import { loginUser } from "../services/authService";
import { useNavigate } from "react-router-dom";
function Login() {
const navigate = useNavigate();
  const handleSubmit = async(e)=>{
    e.preventDefault();
    const loginData={
      email:e.target.email.value,
      password:e.target.password.value,
    };
    try{
      const response=await loginUser(loginData);
      localStorage.setItem("token", response.token);
      localStorage.setItem("role", response.role);
      alert("Login successful");

    if (response.role === "ROLE_VENDOR") {
      navigate("/seller-dashboard");
    } else {
      navigate("/");
    }

    } catch (error) {
      console.log(error);
      alert("Invalid credentials");
    }
    }




  return (
    <div className="login-page d-flex align-items-center justify-content-center">
      <div className="login-card shadow">

        {/* Header */}
        <div className="login-header text-center">
          <h2>Welcome Back</h2>
          <p>Login to explore handmade creations</p>
        </div>

        {/* Form */}
        <form className="login-form"  onSubmit={handleSubmit}>

          <div className="mb-3">
            <label className="form-label">Email address</label>
            <input
              type="email"
              name="email"
              className="form-control"
              placeholder="Enter your email"
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Password</label>
            <input
              type="password"
              name="password"
              className="form-control"
              placeholder="Enter your password"
            />
          </div>

          <div className="d-flex justify-content-between align-items-center mb-3">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                id="rememberMe"
              />
              <label className="form-check-label" htmlFor="rememberMe">
                Remember me
              </label>
            </div>

            <span className="forgot-password">Forgot password?</span>
          </div>

          <button type="submit" className="btn btn-primary w-100">
            Login
          </button>

        </form>

        {/* Divider */}
        <div className="login-divider">
          <span>OR</span>
        </div>

        {/* Footer */}
        <div className="login-footer text-center">
          <p>
            Don’t have an account? <span className="signup-link"><Link to="/register-customer" className="signup-link">
  Sign up
</Link>
</span>
          </p>
        </div>

      </div>
    </div>
  );

}
export default Login;
