import http from "../http-common";

class PaymentService {
  getAll(data, tokenId, value) {
    const headers = {};
    if (tokenId) {
        headers.token = tokenId;
        headers.amount = value;
    }
    return http.get("/stripe/payment", data,
     { headers });
  }

}

export default new PaymentService();