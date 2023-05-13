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
        
        <Route path="*" element={<><h1>Not found</h1></>} />
      </Routes>
    </div>
  );
}

export default App;