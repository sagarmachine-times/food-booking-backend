package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    Optional<Coupon> findByName(String name);
}
