package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.RestaurantDto;
import in.timesinternet.foodbooking.dto.request.RestaurantUpdateDto;
import in.timesinternet.foodbooking.entity.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(RestaurantDto restaurantDto);

    void updateRestaurantLogo(MultipartFile file, Integer restaurantId, String email);

    Restaurant getRestaurant(Integer restaurantId);

    Restaurant updateRestaurant(RestaurantUpdateDto restaurantUpdateDto, Integer restaurantId);

    List<Restaurant> getAllRestaurant();
}
