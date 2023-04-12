package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.Order_item;
@Repository
public interface Order_itemRepository extends JpaRepository<Order_item, Integer>, PagingAndSortingRepository<Order_item, Integer> {

}
