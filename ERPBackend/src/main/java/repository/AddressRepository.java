package repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>,PagingAndSortingRepository<Address, Integer> {

	Collection<Address> findAddressByStreetIgnoreCase(String street);

	
}
