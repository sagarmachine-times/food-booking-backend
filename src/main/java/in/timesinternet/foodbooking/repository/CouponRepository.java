package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

}
