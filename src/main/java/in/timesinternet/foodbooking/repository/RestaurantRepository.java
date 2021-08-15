package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> findByRestaurantDetailSubDomain(String subDomain);
}
