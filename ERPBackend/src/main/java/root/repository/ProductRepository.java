package root.repository;


import java.util.Collection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,PagingAndSortingRepository<Product, Integer>{

	Collection<Product> findProductByProductNameIgnoreCase(String product_name);

}
