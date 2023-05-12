import { useRef, useState } from "react"
import addressService from "../../services/address.service";

export default function AddressAdd() {

    const streetRef = useRef(null);
    const countryRef = useRef(null);
    const cityRef = useRef(null);

    const [error, setError] = useState(false);

    function save() {
        
        const street = streetRef.current.value;
        const country = countryRef.current.value;
        const city = cityRef.current.value;
        
        if (!street || !country || !city) {
            setError(true);
            return;
        }

        addressService.create({
            street,
            country,
            city
        })
        .then(data => {
            console.log("Successfully saved")
            setError(false);
            alert("Successfully saved");
        })
        .catch(err => {
            console.log(err)
            alert(err);
        })
    }

    return (
        <>
            <h1>New address</h1>

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
                        <label>Country</label>
                        <input ref={countryRef} className="form-control" name="country" />
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