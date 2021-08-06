package in.timesinternet.foodbooking.contoller.admin;


import in.timesinternet.foodbooking.dto.request.RestaurantDto;
import in.timesinternet.foodbooking.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Object addRestaurant(@RequestBody RestaurantDto restaurantDto){
       return restaurantService.createRestaurant(restaurantDto);
    }

}
