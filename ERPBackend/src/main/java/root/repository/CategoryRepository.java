package root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import root.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>,PagingAndSortingRepository<Category, Integer> {

	Category findCategoryByCategoryNameIgnoreCase(String category_name);
}
