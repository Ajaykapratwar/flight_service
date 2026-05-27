import './App.css'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import ListFlights from './components/list-flights'
import AddFlight from './components/add-flight'
import FindCode from './components/find-code'
import FindCarrier from './components/find-carrier'
import FindRoute from './components/find-route'
import FindPrice from './components/find-price'

export default function App() {
  return (
    <div className="container-fluid p-0">
      <BrowserRouter>
      <nav className="navbar navbar-expand-sm navbar-custom px-3">
        <Link to="/" className="navbar-brand fw-bold">Flight-App</Link>
        <ul className="navbar-nav me-auto">
          <li className="nav-item">
            <Link to="/add" className="nav-link">Add Flight</Link>
          </li>
          <li className="nav-item">
            <Link to="/list" className="nav-link">List Flights</Link>
          </li>
          <li className="nav-item">
            <Link to="/code" className="nav-link">Find Code</Link>
          </li>
          <li className="nav-item">
            <Link to="/carrier" className="nav-link">Find Carrier</Link>
          </li>
          <li className="nav-item">
            <Link to="/route" className="nav-link">Find Route</Link>
          </li>
          <li className="nav-item">
            <Link to="/price" className="nav-link">Find Price</Link>
          </li>
        </ul>
      </nav>
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<ListFlights />} />
          <Route path="/list" element={<ListFlights />} />
          <Route path="/add" element={<AddFlight />} />
          <Route path="/code" element={<FindCode />} />
          <Route path="/carrier" element={<FindCarrier />} />
          <Route path="/route" element={<FindRoute />} />
          <Route path="/price" element={<FindPrice />} />
          <Route path="*" element={<div className="alert alert-danger">Page Not Found</div>} />
        </Routes>
      </div>
      </BrowserRouter>
    </div>
  )
}
