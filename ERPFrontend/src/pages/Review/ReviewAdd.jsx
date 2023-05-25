import { useEffect, useRef, useState } from "react"
import productService from "../../services/product.service";
import reviewService from "../../services/review.service";

export default function ReviewAdd() {

    const [products, setProducts] = useState([]);

    const ratingRef = useRef(null);
    const productRef = useRef(null);
    const commentRef = useRef(null);

    const [error, setError] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            const productsData = await productService.getAll();
            setProducts(productsData.data);
        }

        fetchData();
    }, []);

    function save() {
        
        const comment = commentRef.current.value;
        const rating = ratingRef.current.value;
        const product = products.find(x => x.productId === +productRef.current.value);
        
        if (!comment || !rating || !product) {
            setError(true);
            return;
        }

        if (rating < 0 || rating > 5) {
            alert("Wrong value entered for rating");
            return;
        }

        reviewService.create({
            product,
            rating,
            comment,
            user: {
                userId: localStorage.getItem('userId')
            }
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
            <h1>New review</h1>

            <div className="w-50 m-auto">
                <form>
                        <div className="form-group mt-2">
                            <label>Product</label>
                            <select ref={productRef} className="form-control">
                                {products.map((p, idx) => (
                                    <option key={idx} value={p.productId}>{p.name}</option>
                                ))}
                            </select>
                        </div>

                        <div className="form-group mt-2">
                            <label>Rating</label>
                            <input type="number" ref={ratingRef} min={0} max={5} className="form-control" name="price" />
                        </div>

                        <div className="form-group mt-2">
                            <label>Comment</label>
                            <textarea ref={commentRef} className="form-control"></textarea>
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