package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.CouponDto;
import in.timesinternet.foodbooking.dto.request.UpdateCouponDto;
import in.timesinternet.foodbooking.entity.Coupon;
import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.repository.CouponRepository;
import in.timesinternet.foodbooking.repository.ImageRepository;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.CouponService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    CouponRepository couponRepository;
    @Override
    public Coupon addCoupon(CouponDto couponDto, Integer restaurantId) {
        Integer imageId = couponDto.getImageId();
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        Optional<Image> imageOptional = imageRepository.findById(imageId);
        if(restaurantOptional.isPresent() && imageOptional.isPresent())
        {
            Restaurant restaurant = restaurantOptional.get();
            Image image = imageOptional.get();
            ModelMapper modelMapper=new ModelMapper();
           Coupon coupon= modelMapper.map(couponDto,Coupon.class);
           coupon.setBanner(image);
           coupon.setRestaurant(restaurant);
           // restaurant.addCoupon(coupon);
           return couponRepository.save(coupon);

        }
        else
        {
            throw new RuntimeException("Runtime");
        }
    }
    @Override
    public Coupon updateCoupon(UpdateCouponDto updateCouponDto,Integer couponId,Integer restaurantId)
    {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        Optional<Image> optionalImage=imageRepository.findById(updateCouponDto.getImageId());

        if (optionalCoupon.isPresent() && optionalImage.isPresent()) {

            Image image=optionalImage.get();
            Coupon coupon=optionalCoupon.get();
            Restaurant restaurant = coupon.getRestaurant();
            if (restaurant.getId() == restaurantId) {
                coupon.setName(updateCouponDto.getName());
                coupon.setMaxDiscount(updateCouponDto.getMaxDiscount());
                coupon.setMaxPerUser(updateCouponDto.getMaxPerUser());
                coupon.setEndingDate(updateCouponDto.getEndingDate());
                coupon.setValue(updateCouponDto.getValue());
                coupon.setMinimumCartValue(updateCouponDto.getMinimumCartValue());
                coupon.setStartingDate(updateCouponDto.getStartingDate());
                coupon.setBanner(image);

               return couponRepository.save(coupon);

            } else {
                throw new RuntimeException("You are not authorised to update this coupon");
            }
        }
        else {
            throw new RuntimeException(" Coupon does not exist of Id "+ couponId);
        }
    }
    @Override
    public Coupon deleteCoupon(Integer couponId,Integer restaurantId)
    {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);

        if (optionalCoupon.isPresent() ) {


            Coupon coupon=optionalCoupon.get();
            Restaurant restaurant = coupon.getRestaurant();
            if (restaurant.getId() == restaurantId) {


                 couponRepository.delete(coupon);
                 return coupon;

            } else {
                throw new RuntimeException("You are not authorised to update this coupon");
            }
        }
        else {
            throw new RuntimeException(" Coupon does not exist of Id "+ couponId);
        }
    }

    @Override
    public List<Coupon> getAllCoupon(Integer restaurantId)
    {

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            return restaurant.getCouponList();
        } else {
            throw new RuntimeException("This Restaurant does not exist");
        }
    }

    @Override
    public Coupon getCoupon(Integer couponId) {
        Optional<Coupon> couponOptional = couponRepository.findById(couponId);
        if (couponOptional.isPresent())
             return  couponOptional.get();
        throw  new RuntimeException("coupon doesn't exist");
    }
}