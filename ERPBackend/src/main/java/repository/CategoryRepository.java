package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>,PagingAndSortingRepository<Category, Integer> {

	Category findCategoryByCategoryNameIgnoreCase(String category_name);
}
