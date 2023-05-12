import http from "../http-common";

class AddressService {
  getAll() {
    return http.get("/address/address");
  }

  get(id) {
    return http.get(`/address/address/${id}`);
  }

  create(data) {
    return http.post("/address/address", data);
  }

  update(id, data) {
    return http.put(`/address/address/${id}`, data);
  }

  delete(id) {
    return http.delete(`/address/address/${id}`);
  }

}

export default new AddressService();