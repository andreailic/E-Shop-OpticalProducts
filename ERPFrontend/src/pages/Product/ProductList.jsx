import { useEffect, useMemo, useState } from "react"
import { Link } from "react-router-dom";
import FilterComponent from "../../components/Filter/FilterComponent";
import DataTable from "react-data-table-component";
import productService from "../../services/product.service";

export default function ProductList() {

    //Const 
    const columns = [
        {
            name: 'Id',
            selector: row => row.productId,
            sortable: true,
        },
        {
            name: 'Name',
            selector: row => row.name,
            sortable: true,
        },
        {
            name: 'Category',
            selector: row => row.category.categoryName,
            sortable: true,
        },
        {
            name: 'Brand',
            selector: row => row.brand.brandName,
            sortable: true,
        },
        {
            name: 'Price',
            selector: row => row.price,
            sortable: true,
        },
        {
            name: 'Description',
            selector: row => row.description,
            sortable: true,
        },
        {
            name: 'Lager',
            selector: row => row.lager,
            sortable: true,
        },
        {
            cell:(row) => <Link className="btn btn-warning" to={"/product/edit/" + row.productId}>Edit</Link>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
        {
            cell:(row) => <button className="btn btn-danger" onClick={() => deleteRecord(row.productId)} id={row.productId}>Delete</button>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
      ];

    // States
    const [data, setData] = useState([]);
    const [filterText, setFilterText] = useState('');
    const [filteredData, setFilteredData] = useState([]);	

    useEffect(() => {
       productService.getAll().then(response => {
        console.log(response.data)
        setData(response.data);
        setFilteredData(response.data);
       })
    }, []);

    useEffect(() => {
        setFilteredData(data.filter(item => item.name.toLowerCase().includes(filterText.toLowerCase())));
      }, [filterText]);

    const searchMemo = useMemo(() => {
        const handleClear = () => {
            if (filterText) {
                setFilterText('');
            }
        };

        return (
            <FilterComponent onFilter={e => setFilterText(e.target.value)} onClear={handleClear} filterText={filterText} />
        );
    }, [filterText]);

    function deleteRecord(id) {
        productService.delete(id).then(response => {
            const newData = data.filter(x => x.productId !== id);
            const newFilteredData = filteredData.filter(x => x.productId !== id);
            setData(newData);
            setFilteredData(newFilteredData);
        }).catch(err => {
            console.log(err);
        });
    }

    return (
        <>
            <div>
                <Link to="/product/add" className="btn btn-success">New product</Link>
            </div>

            <div className="col-md-12">
            <DataTable
                    title="Product List"
                    columns={columns}
                    data={filteredData}
                    pagination
                    subHeader
                    subHeaderComponent={searchMemo}
                    selectableRows
                    persistTableHead
                />
        </div>  

        </>
    )
}