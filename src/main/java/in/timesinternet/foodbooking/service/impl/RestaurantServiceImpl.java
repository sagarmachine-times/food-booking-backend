package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.RestaurantDto;
import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.entity.embeddable.RestaurantDetail;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    ModelMapper modelMapper= new ModelMapper();

    @Override
    @Transactional
    public Restaurant createRestaurant(RestaurantDto restaurantDto) {

        RestaurantDetail restaurantDetail=  modelMapper.map(restaurantDto,RestaurantDetail.class);
        Restaurant restaurant= new Restaurant();
        restaurant.setRestaurantDetail(restaurantDetail);

        Staff staff = new Staff();
        staff.setEmail(restaurantDetail.getEmail());
        staff.setPassword("1234");
        staff.setRole(Role.ROLE_OWNER);
        staff.setFirstName("owner");
        staff.setLastName("owner");

        restaurant.addStaff(staff);
      return   restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public void updateRestaurantLogo(MultipartFile file, Integer restaurantId, String email) {
        //authorize user

        Restaurant restaurant= restaurantRepository.findById(restaurantId).get();

        //upload file
        String url="https://image.shutterstock.com/image-photo/surreal-image-african-elephant-wearing-260nw-1365289022.jpg";
        restaurant.setLogo(new Image(url, url, url));
        restaurantRepository.save(restaurant);
    }


}
