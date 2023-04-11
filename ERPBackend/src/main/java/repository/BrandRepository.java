package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>,PagingAndSortingRepository<Brand, Integer>  {

	Brand findBrandByBrandNameIgnoreCase(String brand_name);
}
