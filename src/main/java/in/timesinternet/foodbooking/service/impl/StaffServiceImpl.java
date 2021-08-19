package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.StaffDto;
import in.timesinternet.foodbooking.dto.request.StaffUpdateDto;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.exception.UnauthorizedException;
import in.timesinternet.foodbooking.exception.UserAlreadyExistException;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.repository.StaffRepository;
import in.timesinternet.foodbooking.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Staff> createStaff(Integer restaurantId, StaffDto staffDto) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        Restaurant restaurant = restaurantOptional.get();

        ModelMapper modelMapper = new ModelMapper();
        Staff newStaff = modelMapper.map(staffDto, Staff.class);
        newStaff.setPassword(passwordEncoder.encode("123"));
        restaurant.addStaff(newStaff);

        try
        {
            restaurantRepository.save(restaurant);
            return restaurant.getStaffList();
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            throw new UserAlreadyExistException(" staff member with following details already exists ");
        }
    }

    @Override
    public List<Staff> getStaff(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId).get().getStaffList();
    }

    @Override
    public Staff updateStaff(String userEmail, StaffUpdateDto staffUpdateDto) {
        Staff staff = staffRepository.findByEmail(userEmail);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(staffUpdateDto, staff);
        return staffRepository.save(staff);
    }

    @Override
    public Staff deleteStaff(String userEmail, Integer staffId) {
        Staff currentStaff = staffRepository.findByEmail(userEmail);
        Staff staff = staffRepository.findById(staffId).get();
        if (!staff.getRestaurant().getId().equals(currentStaff.getRestaurant().getId()))
            throw new UnauthorizedException("authorized access for deleting the staff");
        staffRepository.deleteById(staffId);
        return staff;
    }


}
