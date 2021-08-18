package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Customer;

import in.timesinternet.foodbooking.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByActualEmailAndRestaurant(String actualEmail, Restaurant restaurantId);

    Optional<Customer> findByIdAndRestaurant(Integer id, Restaurant restaurant);
}
