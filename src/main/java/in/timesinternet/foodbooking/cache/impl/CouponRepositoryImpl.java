package in.timesinternet.foodbooking.cache.impl;

import in.timesinternet.foodbooking.cache.CouponRepository;
import in.timesinternet.foodbooking.entity.Category;
import in.timesinternet.foodbooking.entity.Coupon;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponRepositoryImpl implements CouponRepository {

    RedisTemplate redisTemplate;
    HashOperations hashOperations;

    public CouponRepositoryImpl(RedisTemplate redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void addCoupon(Coupon coupon, Integer restaurantId) {

    }

    @Override
    public void addCouponList(List<Coupon> couponList, Integer restaurantId) {
        hashOperations.put("RESTAURANT_COUPON", restaurantId, couponList);

    }

    @Override
    public void updateCouponList(List<Coupon> couponList, Integer restaurantId) {
        hashOperations.put("RESTAURANT_COUPON", restaurantId, couponList);

    }

    @Override
    public List<Coupon> getCouponList(Integer restaurantId) {
        return (List<Coupon>) hashOperations.get("RESTAURANT_COUPON", restaurantId);

    }

    @Override
    public boolean doesExist(Integer restaurantId) {
        return hashOperations.hasKey("RESTAURANT_COUPON", restaurantId);

    }
}
