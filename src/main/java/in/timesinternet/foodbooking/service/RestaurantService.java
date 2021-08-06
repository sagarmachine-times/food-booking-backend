package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.RestaurantDto;
import in.timesinternet.foodbooking.entity.Restaurant;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {

    Restaurant createRestaurant(RestaurantDto restaurantDto);

    void updateRestaurantLogo(MultipartFile file, Integer restaurantId, String email);

}
