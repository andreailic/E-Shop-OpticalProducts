import React, { useEffect, useState } from "react";
import productService from "../../services/product.service";
import reviewService from "../../services/review.service";
import DataTable from "react-data-table-component";
import { Link, useParams } from "react-router-dom";

const ProductOverview = () => {

    const userRole = localStorage.getItem('role');

    const columns = [
        {
            name: 'Id',
            selector: row => row.reviewId,
            sortable: true,
        },
        {
            name: 'Product',
            selector: row => row.product.name,
            sortable: true,
        },
        {
            name: 'Rating',
            selector: row => row.rating,
            sortable: true,
        },
        {
            name: 'Comment',
            selector: row => row.comment,
            sortable: true,
        },
        {
            name: 'User',
            selector: row => row.user.name + " " + row.user.surname,
            sortable: true,
        },
        {
            cell:(row) => userRole && (userRole === "ROLE_CUSTOMER" && row.user.userId === +localStorage.getItem('userId')) && <Link className="btn btn-warning" to={"/review/edit/" + row.reviewId}>Edit</Link>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
    ];

    const {id} = useParams();

    const [product, setProduct] = useState();
    const [data, setData] = useState([]);
    const [filteredData, setFilteredData] = useState([]);	

    useEffect(() => {
        productService.get(id).then(response => {
            setProduct(response.data);

            reviewService.getByProductId(id)
            .then(response => {
                setData(response.data);
                setFilteredData(response.data);
            }).catch(err => {
                console.log('Review error', err);
            })
        }).catch(err => {
            console.log(err);
        })
    }, []);

  return (
    <div className="container">
      <h2 className="mb-4">Product Overview</h2>
      <div className="row">
        <div className="col-md-6">
          <strong>Product ID:</strong> {product?.productId}
        </div>
        <div className="col-md-6">
          <strong>Name:</strong> {product?.name}
        </div>
      </div>
      <div className="row">
        <div className="col-md-6">
          <strong>Brand:</strong> {product?.brand?.brandName}
        </div>
        <div className="col-md-6">
          <strong>Category:</strong> {product?.category?.categoryName}
        </div>
      </div>
      <div className="row">
        <div className="col-md-6">
          <strong>Description:</strong> {product?.description}
        </div>
        <div className="col-md-6">
          <strong>Lager:</strong> {product?.lager}
        </div>
      </div>
      <div className="row">
        <div className="col-md-6">
          <strong>Price:</strong> {product?.price}
        </div>
      </div>

      <div className="row">

      {userRole && <div>
            <Link to="/review/add" className="btn btn-success m-5">New review</Link>
        </div>}
        
      <DataTable
        title="Review List"
        columns={columns}
        data={filteredData}
        pagination
        subHeader
        selectableRows
        persistTableHead
    />
      </div>
    </div>
  );
};

export default ProductOverview;