import http from "../http-common";

class ProductService {
  getAll() {
    return http.get("/product/product");
  }

  get(id) {
    return http.get(`/product/product/${id}`);
  }

  create(data) {
    return http.post("/product/product", data);
  }

  update(id, data) {
    return http.put(`/product/product/${id}`, data);
  }

  delete(id) {
    return http.delete(`/product/product/${id}`);
  }

}

export default new ProductService();