import "../styles/About.css";

const About = () => {
  return (
    <div className="about-page">

      {/* Hero Section */}
      <section className="about-hero">
        <h1>About Our Marketplace</h1>
        <p>
          A platform built to support creative minds and handmade small businesses.
        </p>
      </section>

      {/* Mission Section */}
      <section className="about-mission">
        <h2>Our Mission</h2>
        <p>
          Our mission is to empower small handmade businesses by giving them
          a digital space to showcase and sell their creations. From crochet
          and macramé to resin art and handcrafted décor, we help artisans
          turn passion into sustainable income.
        </p>
      </section>

      {/* Core Values */}
      <section className="about-values">
        <h2>Our Core Values</h2>

        <div className="values-grid">
          <div className="value-card">
            <h3>Creativity First</h3>
            <p>
              We celebrate originality and craftsmanship by supporting
              artists who create unique, handmade products.
            </p>
          </div>

          <div className="value-card">
            <h3>Quality & Authenticity</h3>
            <p>
              Every product reflects effort, skill, and passion — ensuring
              customers receive truly handmade items.
            </p>
          </div>

          <div className="value-card">
            <h3>Community Growth</h3>
            <p>
              We aim to build a supportive community where small businesses
              grow together and reach the right audience.
            </p>
          </div>
        </div>
      </section>

      {/* Journey Section */}
      <section className="about-journey">
        <h2>Our Journey</h2>

        <div className="journey-steps">
          <div>
            <span>Idea</span>
            <p>
              Started with the vision to help handmade creators go digital.
            </p>
          </div>

          <div>
            <span>Development</span>
            <p>
              Built a simple and secure platform focused on usability.
            </p>
          </div>

          <div>
            <span>Today</span>
            <p>
              Connecting creators with customers who value handmade products.
            </p>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="about-cta">
        <h2>Support Handmade. Support Dreams.</h2>
        <p>
          Discover beautiful handmade products or start selling your creations today.
        </p>
        <div className="cta-buttons">
          <button>Browse Products</button>
          <button className="secondary">Become a Seller</button>
        </div>
      </section>

    </div>
  );
};

export default About;
