import http from "../http-common";

class PaymentService {

  pay(data, tokenId, value) {
    const headers = {};
    if (tokenId) {
        headers.token = tokenId;
        headers.amount = value;
    }
    return http.post("/payment/payment", data,
     { headers });
  }

  getAll(data, tokenId, value) {
    return http.get("/stripe/payment");
  }

}

export default new PaymentService();