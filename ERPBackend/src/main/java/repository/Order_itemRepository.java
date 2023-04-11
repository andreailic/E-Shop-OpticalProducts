package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.Order_item;

public interface Order_itemRepository extends JpaRepository<Order_item, Integer>, PagingAndSortingRepository<Order_item, Integer> {

}
