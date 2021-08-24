package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Serviceability;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceabilityRepository extends JpaRepository<Serviceability,Integer> {
Optional<Serviceability> findByPincodeAndRestaurantId(Integer pincode, Integer restaurantId);
}
