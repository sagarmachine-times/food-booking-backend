package in.timesinternet.foodbooking.cache;

import in.timesinternet.foodbooking.entity.Coupon;

import java.util.List;

public interface CouponRepository {

    void addCoupon(Coupon coupon, Integer restaurantId);

    void addCouponList(List<Coupon> couponList, Integer restaurantId);

    void updateCouponList(List<Coupon> couponList, Integer restaurantId);

    List<Coupon> getCouponList(Integer restaurantId);

    boolean doesExist(Integer restaurantId);
}
