import { useEffect, useMemo, useState } from "react"
import { Link } from "react-router-dom";
import FilterComponent from "../../components/Filter/FilterComponent";
import DataTable from "react-data-table-component";
import reviewService from "../../services/review.service";

export default function ReviewList() {

    const userRole = localStorage.getItem('role');

    //Const 
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

        {
            cell:(row) => userRole && (userRole === "ROLE_CUSTOMER" && row.user.userId === +localStorage.getItem('userId')) && <button className="btn btn-danger" onClick={() => deleteRecord(row.reviewId)} id={row.reviewId}>Delete</button>,
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
       reviewService.getAll().then(response => {
        console.log(response.data)
        setData(response.data);
        setFilteredData(response.data);
       })
    }, []);

    useEffect(() => {
        setFilteredData(data.filter(item => item.product.name.toLowerCase().includes(filterText.toLowerCase())));
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
        reviewService.delete(id).then(response => {
            const newData = data.filter(x => x.reviewId !== id);
            const newFilteredData = filteredData.filter(x => x.reviewId !== id);
            setData(newData);
            setFilteredData(newFilteredData);
        }).catch(err => {
            console.log(err);
        });
    }

    return (
        <>

            <div className="text-left m-5">
                <img src='/rating.png' alt='login' width={100} height={100} />
            
            </div>

            {userRole && <div>
                <Link to="/review/add" className="btn btn-success m-5">New review</Link>
            </div>}



            <div className="col-md-12">
            <DataTable
                    title="Review List"
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