package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.StaffDto;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public Object createStaff(Integer restaurantId, StaffDto staffDto) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        Restaurant restaurant = restaurantOptional.get();

        ModelMapper modelMapper= new ModelMapper();
        Staff newStaff= modelMapper.map(staffDto, Staff.class);
        restaurant.addStaff(newStaff);
  return null;
    }
}
