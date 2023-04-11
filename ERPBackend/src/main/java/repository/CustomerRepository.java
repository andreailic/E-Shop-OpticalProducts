package repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>,PagingAndSortingRepository<Customer, Integer> {

	Collection<Customer> findCustomerByNameIgnoreCase(String name);

	Customer findByEmail(String email);

	
}
