package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.dto.request.RestaurantResponseDto;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.RestaurantDetail;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.CustomerRepository;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        ModelMapper modelMapper= new ModelMapper();

        Restaurant restaurant = restaurantRepository.findById(customerDto.getRestaurantId()).get();
        Customer customer =modelMapper.map(customerDto, Customer.class);
        customer.setRestaurant(restaurant);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(Role.ROLE_CUSTOMER);
        return customerRepository.save(customer);
    }

    @Override
    public RestaurantResponseDto getRestaurantDetail(String subDomain)
    {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByRestaurantDetailSubDomain(subDomain);

        if (restaurantOptional.isPresent())
        {
            RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

            Restaurant restaurant = restaurantOptional.get();

            restaurantResponseDto.setId(restaurant.getId());
            RestaurantDetail restaurantDetail = restaurant.getRestaurantDetail();

            restaurantResponseDto.setStatus(restaurantDetail.getStatus());
            restaurantResponseDto.setOpeningTime(restaurantDetail.getOpeningTime());
            restaurantResponseDto.setClosingTime(restaurantDetail.getClosingTime());
            restaurantResponseDto.setName(restaurantDetail.getName());
            restaurantResponseDto.setEmail(restaurantDetail.getEmail());
            restaurantResponseDto.setSubDomain(restaurantDetail.getSubDomain());
            restaurantResponseDto.setAddress(restaurantDetail.getAddress());

            restaurantResponseDto.setLogo(restaurant.getLogo());

            return  restaurantResponseDto;
        }
        else
        {
          throw new RuntimeException("restaurant not found");
        }
    }


}
