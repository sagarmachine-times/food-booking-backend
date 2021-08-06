package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByEmail(String email);
}
