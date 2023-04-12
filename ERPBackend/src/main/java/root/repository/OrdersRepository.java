package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.Orders;
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>, PagingAndSortingRepository<Orders, Integer> {

}
