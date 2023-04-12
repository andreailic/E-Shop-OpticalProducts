package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.Orders;
import root.model.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>,PagingAndSortingRepository<Payment, Integer> {

	Payment findPaymentByOrdersIgnoreCase(Orders orders);
}
