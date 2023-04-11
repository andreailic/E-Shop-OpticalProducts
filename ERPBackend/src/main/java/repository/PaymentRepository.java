package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import model.Orders;
import model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>,PagingAndSortingRepository<Payment, Integer> {

	Payment findPaymentByOrdersIgnoreCase(Orders orders);
}
