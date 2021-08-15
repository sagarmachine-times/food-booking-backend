package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.dto.request.RestaurantResponseDto;
import in.timesinternet.foodbooking.entity.Customer;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);

    RestaurantResponseDto getRestaurantDetail(String subDomain);

}
