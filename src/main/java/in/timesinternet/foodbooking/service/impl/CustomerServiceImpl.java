package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.CustomerRepository;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        customer.setActualEmail(customer.getEmail());
        customer.setEmail(customer.getEmail()+"_"+restaurant.getRestaurantDetail().getSubDomain());
       return customerRepository.save(customer);
    }
}
