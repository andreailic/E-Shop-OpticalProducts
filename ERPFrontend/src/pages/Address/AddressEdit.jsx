import { useEffect, useRef, useState } from "react"
import { useParams } from "react-router-dom";
import addressService from "../../services/address.service";

export default function AddressEdit() {

    const streetRef = useRef(null);
    const cityRef = useRef(null);
    const stateRef = useRef(null);

    const [error, setError] = useState(false);

    const {id} = useParams();

    useEffect(() => {
        addressService.get(id)
            .then(response => {
                streetRef.current.value = response.data.street;
                cityRef.current.value = response.data.city;
                stateRef.current.value = response.data.country;
            })
            .catch(err => {
                console.log(err);
            })
    }, []);

    function save() {
        
        const street = streetRef.current.value;
        const city = cityRef.current.value;
        const country = stateRef.current.value;
        
        if (!street || !city || !country) {
            setError(true);
            return;
        }

        addressService.update(id, {
            street,
            city,
            country
        })
        .then(data => {
            console.log("Successfully updated")
            setError(false);
            alert("Successfully updated");
        })
        .catch(err => {
            console.log(err)
            alert(err);
        })
    }

    return (
        <>
            <h1>Edit brand</h1>

            <div className="w-50 m-auto">
                <form>
                    <div className="form-group mt-2">
                        <label>Street</label>
                        <input ref={streetRef} className="form-control" name="street" />
                    </div>

                    <div className="form-group mt-2">
                        <label>City</label>
                        <input ref={cityRef} className="form-control" name="city" />
                    </div>

                    <div className="form-group mt-2">
                        <label>State</label>
                        <input ref={stateRef} className="form-control" name="state" />
                    </div>
                   
                    <div className="form-group mt-2">
                        <button type="button" className="btn btn-primary" onClick={save}>Save</button>
                    </div>

                    {error && <div className="mt-2 bg-danger text-center">All fields are required</div>}
                </form>
            </div>
        </>
    )
}