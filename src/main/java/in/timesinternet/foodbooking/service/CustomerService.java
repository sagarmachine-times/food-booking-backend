package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CategoryUpdateDto;
import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.dto.request.RestaurantResponseDto;
import in.timesinternet.foodbooking.entity.Coupon;
import in.timesinternet.foodbooking.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);

    RestaurantResponseDto getRestaurantDetail(String subDomain);

    List<CategoryUpdateDto> getRestaurantCategory(Integer restaurantId);


}
