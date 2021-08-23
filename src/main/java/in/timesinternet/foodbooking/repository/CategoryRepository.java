package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByRestaurantId(Integer restaurantId);

    Category deleteCategoryById(Integer categoryId);


    Boolean existsByNameAndRestaurantId(String name, Integer restaurantId);
}
