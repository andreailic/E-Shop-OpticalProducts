import { useEffect, useRef, useState } from "react"
import productService from "../../services/product.service";
import brandService from "../../services/brand.service";
import categoryService from "../../services/category.service";

export default function ProductAdd() {

    const [brands, setBrands] = useState([]);
    const [categories, setCategories] = useState([]);

    const nameRef = useRef(null);
    const categoryRef = useRef(null);
    const brandRef = useRef(null);
    const descriptionRef = useRef(null);
    const lagerRef = useRef(null);
    const priceRef = useRef(null);

    const [error, setError] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            const brands = await brandService.getAll();
            setBrands(brands.data);

            const categories = await categoryService.getAll();
            setCategories(categories.data);
        }

        fetchData();
    }, []);

    function save() {
        
        const name = nameRef.current.value;
        const description = descriptionRef.current.value;
        const price = priceRef.current.value;
        const lager = lagerRef.current.value;
        const brand = brands.find(x => x.brandId === +brandRef.current.value);
        const category = categories.find(x => x.categoryId === +categoryRef.current.value);
        
        if (!name || !price || !description || !lager || !brand || !category) {
            setError(true);
            return;
        }

        productService.create({
            name,
            description,
            price,
            lager,
            category,
            brand
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
            <h1>New product</h1>

            <div className="w-50 m-auto">
                <form>
                    <div className="form-group mt-2">
                        <label>Name</label>
                        <input ref={nameRef} className="form-control" name="productName" />
                    </div>

                    <div className="form-group mt-2">
                        <label>Price</label>
                        <input type="number" ref={priceRef} className="form-control" name="price" />
                    </div>

                    <div className="form-group mt-2">
                        <label>Description</label>
                        <textarea ref={descriptionRef} className="form-control"></textarea>
                    </div>

                    <div className="form-group mt-2">
                        <label>Price</label>
                        <input type="number" ref={lagerRef} className="form-control" name="lager" />
                    </div>

                    <div className="form-group mt-2">
                        <label>Category</label>
                        <select ref={categoryRef} className="form-control">
                            {categories.map((c, idx) => (
                                <option key={idx} value={c.categoryId}>{c.categoryName}</option>
                            ))}
                        </select>
                    </div>

                    <div className="form-group mt-2">
                        <label>Brand</label>
                        <select ref={brandRef} className="form-control">
                            {brands.map((b, idx) => (
                                <option key={idx} value={b.brandId}>{b.brandName}</option>
                            ))}
                        </select>
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