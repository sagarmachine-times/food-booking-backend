package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
