import { useEffect, useMemo, useState } from "react"
import { Link } from "react-router-dom";
import brandService from "../../services/brand.service";
import FilterComponent from "../../components/Filter/FilterComponent";
import DataTable from "react-data-table-component";

export default function BrandList() {

    //Const 
    const columns = [
        {
            name: 'Id',
            selector: row => row.brandId,
            sortable: true,
        },
        {
            name: 'Name',
            selector: row => row.brandName,
            sortable: true,
        },
        {
            cell:(row) => <Link className="btn btn-warning" to={"/brands/edit/" + row.brandId}>Edit</Link>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
        {
            cell:(row) => <button className="btn btn-danger" onClick={() => console.log(row)} id={row.ID}>Delete</button>,
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
       brandService.getAll().then(response => {
        console.log(response.data)
        setData(response.data);
        setFilteredData(response.data);
       })
    }, []);

    useEffect(() => {
        setFilteredData(data.filter(item => item.brandName.toLowerCase().includes(filterText.toLowerCase())));
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

    return (
        <>
            <div>
                <Link to="/brands/add" className="btn btn-success">New brand</Link>
            </div>

            <div className="col-md-12">
            <DataTable
                    title="Brands List"
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