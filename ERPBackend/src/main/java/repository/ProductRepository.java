package repository;


import java.util.Collection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>,PagingAndSortingRepository<Product, Integer>{

	Collection<Product> findProductByProductNameIgnoreCase(String product_name);

}