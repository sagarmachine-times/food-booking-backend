package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
