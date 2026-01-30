import "../styles/Footer.css";

const Footer = () => {
  return (
    <footer className="footer">

      <div className="footer-container">

        {/* Brand */}
        <div className="footer-brand">
          <h2>ShopCircle</h2>
          <p>
            Supporting handmade small businesses like crochet, macramé,
            resin art, and handcrafted décor.
          </p>
        </div>

        {/* Links */}
        <div className="footer-links">
          <h4>Quick Links</h4>
          <ul>
            <li>Home</li>
            <li>Stores</li>
            <li>About</li>
            <li>Contact</li>
          </ul>
        </div>

        {/* Seller */}
        <div className="footer-links">
          <h4>For Creators</h4>
          <ul>
            <li>Become a Seller</li>
            <li>Seller Dashboard</li>
            <li>How It Works</li>
          </ul>
        </div>

        {/* Newsletter */}
        <div className="footer-newsletter">
          <h4>Stay Connected</h4>
          <p>Get updates on new handmade products.</p>

          <div className="newsletter-input">
            <input type="email" placeholder="Enter your email" />
            <button>Sign Up</button>
          </div>
        </div>

      </div>

      {/* Bottom */}
      <div className="footer-bottom">
        <p>© {new Date().getFullYear()} ShopCircle. All rights reserved.</p>
        <div className="footer-policy">
          <span>Privacy Policy</span>
          <span>Terms of Service</span>
        </div>
      </div>

    </footer>
  );
};

export default Footer;
