package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.RestaurantDto;
import in.timesinternet.foodbooking.dto.request.RestaurantUpdateDto;
import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.entity.embeddable.RestaurantDetail;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.RestaurantService;
import in.timesinternet.foodbooking.util.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ImageService imageService;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public Restaurant createRestaurant(RestaurantDto restaurantDto) {

        RestaurantDetail restaurantDetail = modelMapper.map(restaurantDto, RestaurantDetail.class);
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantDetail(restaurantDetail);

        Staff staff = new Staff();
        staff.setEmail(restaurantDetail.getEmail());
        staff.setPassword(passwordEncoder.encode("123"));
        staff.setRole(Role.ROLE_OWNER);
        staff.setFirstName("owner");
        staff.setLastName("owner");

        restaurant.addStaff(staff);
        return restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public void updateRestaurantLogo(MultipartFile file, Integer restaurantId, String email) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Image image = imageService.uploadImage(file);
        restaurant.setLogo(image);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId).get();
    }

    @Override
    public Restaurant updateRestaurant(RestaurantUpdateDto restaurantUpdateDto, Integer restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(restaurantUpdateDto, restaurant.getRestaurantDetail());
        return restaurantRepository.save(restaurant);
    }


}
