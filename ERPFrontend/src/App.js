import React from "react";
import { Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Navbar from "./components/Navbar/Navbar";
import BrandAdd from "./pages/Brand/BrandAdd";
import BrandList from "./pages/Brand/BrandList";
import EditBrand from "./pages/Brand/EditBrand";
import AddressList from "./pages/Address/AddressList";
import AddressAdd from "./pages/Address/AddressAdd";
import AddressEdit from "./pages/Address/AddressEdit";
import CategoryList from "./pages/Category/CategoryList";
import CategoryAdd from "./pages/Category/CategoryAdd";
import CategoryEdit from "./pages/Category/CategoryEdit";
import ProductList from "./pages/Product/ProductList";
import ProductAdd from "./pages/Product/ProductAdd";
import ProductEdit from "./pages/Product/ProductEdit";
import ReviewList from "./pages/Review/ReviewList";
import ReviewAdd from "./pages/Review/ReviewAdd";
import ReviewEdit from "./pages/Review/ReviewEdit";
import CustomerList from "./pages/Customer/CustomerList";
import CustomerAdd from "./pages/Customer/CustomerAdd";
import CustomerEdit from "./pages/Customer/CustomerEdit";
import StaffList from "./pages/Staff/StaffList";
import StaffAdd from "./pages/Staff/StaffAdd";
import StaffEdit from "./pages/Staff/StaffEdit";

function App() {
  return (
    <div className="App">
      <Navbar />
      <Routes>
        <Route path="/brands/edit/:id" exact element={<EditBrand />} />
        <Route path="/brands/add" exact element={<BrandAdd />} />
        <Route path="/brands" exact element={<BrandList />} />

        <Route path="/address/edit/:id" exact element={<AddressEdit />} />
        <Route path="/address/add" exact element={<AddressAdd />} />
        <Route path="/address" exact element={<AddressList />} />

        <Route path="/category/edit/:id" exact element={<CategoryEdit />} />
        <Route path="/category/add" exact element={<CategoryAdd />} />
        <Route path="/category" exact element={<CategoryList />} />

        <Route path="/product/edit/:id" exact element={<ProductEdit />} />
        <Route path="/product/add" exact element={<ProductAdd />} />
        <Route path="/product" exact element={<ProductList />} />

        <Route path="/review/edit/:id" exact element={<ReviewEdit />} />  
        <Route path="/review/add" exact element={<ReviewAdd />} />
        <Route path="/review" exact element={<ReviewList />} />

        <Route path="/customer/edit/:id" exact element={<CustomerEdit />} />  
        <Route path="/customer/add" exact element={<CustomerAdd />} />
        <Route path="/customer" exact element={<CustomerList />} />

        <Route path="/staff/edit/:id" exact element={<StaffEdit />} />  
        <Route path="/staff/add" exact element={<StaffAdd />} />
        <Route path="/staff" exact element={<StaffList />} />
        
        <Route path="*" element={<><h1>Not found</h1></>} />
      </Routes>
    </div>
  );
}

export default App;