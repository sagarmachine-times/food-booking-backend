package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
