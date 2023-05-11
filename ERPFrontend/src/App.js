import React from "react";
import { Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Navbar from "./components/Navbar/Navbar";
import BrandAdd from "./pages/Brand/BrandAdd";
import BrandList from "./pages/Brand/BrandList";
import EditBrand from "./pages/Brand/EditBrand";

function App() {
  return (
    <div className="App">
      <Navbar />
      <Routes>
        <Route path="/brands/edit/:id" exact element={<EditBrand />} />
        <Route path="/brands/add" exact element={<BrandAdd />} />
        <Route path="/brands" exact element={<BrandList />} />
        
        <Route path="*" element={<><h1>Not found</h1></>} />
      </Routes>
    </div>
  );
}

export default App;