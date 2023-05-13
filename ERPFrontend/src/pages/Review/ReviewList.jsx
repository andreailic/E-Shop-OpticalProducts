import { useEffect, useMemo, useState } from "react"
import { Link } from "react-router-dom";
import FilterComponent from "../../components/Filter/FilterComponent";
import DataTable from "react-data-table-component";
import reviewService from "../../services/review.service";

export default function ReviewList() {

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
            cell:(row) => <Link className="btn btn-warning" to={"/review/edit/" + row.reviewId}>Edit</Link>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
        {
            cell:(row) => <button className="btn btn-danger" onClick={() => deleteRecord(row.reviewId)} id={row.reviewId}>Delete</button>,
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
            <div>
                <Link to="/review/add" className="btn btn-success">New review</Link>
            </div>

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