package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByRestaurantId(Integer restaurantId);
         Boolean existsByNameAndCategoryRestaurantId(String name, Integer restaurantId);

//    Category deleteCategoryById(Integer categoryId);
}