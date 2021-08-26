package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CategoryUpdateDto;
import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.dto.request.CustomerUpdateDto;
import in.timesinternet.foodbooking.dto.request.RestaurantResponseDto;
import in.timesinternet.foodbooking.entity.Coupon;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.AddressContact;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);

    RestaurantResponseDto getRestaurantDetail(Integer restaurantId);

    Customer getCustomer(String email);
    Customer getCustomer(Integer id);
    List<Customer> getAllCustomer(Integer restaurantId);
    List<AddressContact> addAddress(AddressContact addressContact, String userEmail);
    List<AddressContact> getAddresses(String userEmail);
    Customer updateCustomerProfile(CustomerUpdateDto customerUpdateDto, String userEmail);


}
