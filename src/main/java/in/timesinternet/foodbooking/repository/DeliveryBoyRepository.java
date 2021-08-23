package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.DeliveryBoy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoy, Integer> {

    Optional<DeliveryBoy> findByEmail(String userEmail);
}
