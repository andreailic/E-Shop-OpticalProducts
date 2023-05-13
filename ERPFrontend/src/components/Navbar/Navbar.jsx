import { NavLink } from 'react-router-dom';

export default function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <NavLink to={"/"} className="nav-link">Pocetna</NavLink>
                    </li>

                    <li className="nav-item">
                        <NavLink className="nav-link " to={"/brands"} >Brands</NavLink>
                    </li>

                    <li className="nav-item">
                        <NavLink className="nav-link " to={"/address"} >Addresses</NavLink>
                    </li>

                    <li className="nav-item">
                        <NavLink className="nav-link " to={"/category"} >Categories</NavLink>
                    </li>

                    <li className="nav-item">
                        <NavLink className="nav-link " to={"/product"} >Products</NavLink>
                    </li>

                    <li className="nav-item">
                        <NavLink className="nav-link " to={"/review"} >Reviews</NavLink>
                    </li>

                    <li className="nav-item">
                        <NavLink className="nav-link " to={"/customer"} >Customers</NavLink>
                    </li>

                    <li className="nav-item">
                        <NavLink className="nav-link " to={"/staff"} >Staff</NavLink>
                    </li>

                </ul>
            </div>
        </nav>
    )
}