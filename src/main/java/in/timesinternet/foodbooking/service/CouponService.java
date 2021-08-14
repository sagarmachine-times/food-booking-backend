package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CouponDto;
import in.timesinternet.foodbooking.dto.request.UpdateCouponDto;
import in.timesinternet.foodbooking.entity.Coupon;

import java.util.List;

public interface CouponService {

    Coupon addCoupon(CouponDto couponDto, Integer restaurantId);

    Coupon updateCoupon(UpdateCouponDto updateCouponDto,Integer couponId,Integer restaurantId);

    Coupon deleteCoupon(Integer couponId, Integer restaurantId);

    List<Coupon> GetAllCoupon(Integer restaurantId);
}
