package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>,PagingAndSortingRepository<Brand, Integer>  {

	Brand findBrandByBrandNameIgnoreCase(String brand_name);
}
