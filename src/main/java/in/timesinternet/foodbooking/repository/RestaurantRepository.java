package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
