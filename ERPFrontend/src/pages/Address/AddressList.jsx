import { useEffect, useMemo, useState } from "react"
import { Link } from "react-router-dom";
import FilterComponent from "../../components/Filter/FilterComponent";
import DataTable from "react-data-table-component";
import addressService from "../../services/address.service";

export default function AddressList() {

    const userRole = localStorage.getItem("role");
    console.log(userRole)

    //Const 
    const columns = [
        {
            name: 'Id',
            selector: row => row.addressId,
            sortable: true,
        },
        {
            name: 'Street',
            selector: row => row.street,
            sortable: true,
        },
        {
            name: 'Country',
            selector: row => row.country,
            sortable: true,
        },
        {
            cell:(row) => userRole && userRole === "ROLE_STAFF" && <Link className="btn btn-warning" to={"/address/edit/" + row.addressId}>Edit</Link>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
        {
            cell:(row) => userRole && userRole === "ROLE_STAFF" && <button className="btn btn-danger" onClick={() => deleteRecord(row.addressId)} id={row.addressId}>Delete</button>,
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
       addressService.getAll().then(response => {
        console.log(response.data)
        setData(response.data);
        setFilteredData(response.data);
       })
    }, []);

    useEffect(() => {
        setFilteredData(data.filter(item => item.street.toLowerCase().includes(filterText.toLowerCase())
        || item.country.toLowerCase().includes(filterText.toLowerCase())));
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
        addressService.delete(id).then(response => {
            const newData = data.filter(x => x.addressId !== id);
            const newFilteredData = filteredData.filter(x => x.addressId !== id);
            setData(newData);
            setFilteredData(newFilteredData);
        }).catch(err => {
            console.log(err);
        });
    }

    return (
        <>
            {userRole && userRole === "ROLE_STAFF" && <div>
                <Link to="/address/add" className="btn btn-success">New address</Link>
            </div>
            }

            <div className="col-md-12">
            <DataTable
                    title="Address List"
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