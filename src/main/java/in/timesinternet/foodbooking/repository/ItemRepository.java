package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByRestaurantId(Integer restaurantId);
<<<<<<< HEAD
    Boolean existByNameAndRestaurantId(String name, Integer restaurantId);
=======
         Boolean existsByNameAndCategoryRestaurantId(String name, Integer restaurantId);
>>>>>>> e4a263e9904cb47b9b953d9076b746b275d67028

//    Category deleteCategoryById(Integer categoryId);
}