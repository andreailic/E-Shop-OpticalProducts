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

}

export default new PaymentService();