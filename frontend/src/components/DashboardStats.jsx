const DashboardStats = ({ stats }) => {
    

  return (
    <div className="d-flex justify-content-between mb-4">
      {stats.map((s, idx) => (
        <div key={idx} className="card text-center p-3 flex-fill mx-2 shadow-sm">
          <h6>{s.title}</h6>
          <h5>{s.value}</h5>
        </div>
      ))}
    </div>
  );
};

export default DashboardStats;
