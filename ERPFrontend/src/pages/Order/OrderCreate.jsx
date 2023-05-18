import { useEffect, useMemo, useState } from "react"
import { useNavigate } from "react-router-dom";
import FilterComponent from "../../components/Filter/FilterComponent";
import DataTable from "react-data-table-component";
import productService from "../../services/product.service";
import orderService from "../../services/order.service";
import StripeCheckout from "react-stripe-checkout";
import paymentService from "../../services/payment.service";

export default function OrderCreate() {

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
            name: 'Price',
            selector: row => row.price,
            sortable: true,
        },
        {
            name: 'Lager',
            selector: row => row.lager,
            sortable: true,
        },
        {
            name: 'Selected quantity',
            selector: row => row.selectedQuantity,
            sortable: true,
        },
        {
            cell:(row) => <button className="btn btn-success" onClick={() => addToBasket(row.productId)} id={"Add" + row.productId}>Add</button>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
        {
            cell:(row) => <button className="btn btn-danger" onClick={() => removeProduct(row.productId)} id={"Delete" + row.productId}>Remove</button>,
            ignoreRowClick: true,
            allowOverflow: true,
            button: true,
        },
      ];

    // States
    const [data, setData] = useState([]);
    const [filterText, setFilterText] = useState('');
    const [filteredData, setFilteredData] = useState([]);	

    const navigate = useNavigate();

    useEffect(() => {
       productService.getAll().then(response => {
        const retrievedData = response.data.map(x => {return {...x, selectedQuantity: 0}});
        setData(retrievedData);
        setFilteredData(retrievedData);
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

    function addToBasket(id) {
        setData(state => {
            state.forEach(x => {
                if (x.productId === +id) {
                    x.selectedQuantity++;
                }
            })
            return state;
        });

        setFilteredData(
            [...filteredData]
        )
    }

    function removeProduct(id) {
        setData(state => {
            state.forEach(x => {
                if (x.productId === +id) {
                    x.selectedQuantity = 0;
                }
            })
            return state;
        });

        setFilteredData(
            [...filteredData]
        )
    }

    async function handleToken(token) {
           
        let total = 0;
        data.forEach(x => total += x.selectedQuantity * x.price);
        const orderItems = data.map(x => { 
            return {
                product: {...x}, 
                quantity: x.selectedQuantity,
            }
        });
        
        orderService.create({
            orderItems,
            user: {
                userId: localStorage.getItem('userId')
            },
            orderPrice: total
        })
        .then(response => {
            paymentService.pay({
                paymentMethod: 'Card',
                order: {
                    orderId: response.data
                }
            }, token.id, total).then(response => {
                alert("Successfully ordered");
                navigate("/product");    
            }).catch(error => {
                console.log(error);
                alert("Payment not successfuul!")
            });
        })
        .catch(err => {

        });

    
    }

    function confirmOrder() {
        let total = 0;
        data.forEach(x => total += x.selectedQuantity * x.price);

        const orderItems = [];
        data.forEach(x => { 
            if (x.selectedQuantity != 0) {
                orderItems.push( {
                    product: {...x}, 
                    quantity: x.selectedQuantity,
                })
            }
        });
        
        orderService.create({
            orderItems,
            user: {
                userId: localStorage.getItem('userId')
            },
            orderPrice: total
        })
        .then(response => {
            paymentService.pay({
                paymentMethod: 'Cash',
                order: {
                    orderId: response.data
                }
            }).then(response => {
                alert("Successfully ordered");
                navigate("/product");    
            }).catch(error => {
                console.log(error);
            })
            
        })
        .catch(err => {
            console.log(err)
        });
    }

    let total = 0;
    data.forEach(x => total += x.selectedQuantity * x.price);

    return (
        <>

            <div className="col-md-12">
            <DataTable
                    title="Choose products to buy"
                    columns={columns}
                    data={filteredData}
                    pagination
                    subHeader
                    subHeaderComponent={searchMemo}
                    selectableRows
                    persistTableHead
                />

            <hr />

            <h3 className="text-right">Total {total}</h3>

            {total === 0 ? null : <div className="text-center">
                <StripeCheckout
                stripeKey={process.env.REACT_APP_STRIPE_KEY}
                token={handleToken}
            />
            </div>
            }

            {total === 0 ? null : <div className="text-center">
                <button title="Total must be different from 0" className="btn btn-success" onClick={confirmOrder}>Cash order</button>
            </div>
            }
        </div>  

        </>
    )
}