package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import  java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> getOrderByCustomer(Customer customer);

    Integer countByCouponId(Integer couponId);

    Integer countByCouponIdAndCustomerId(Integer couponId, Integer customerId);
}
