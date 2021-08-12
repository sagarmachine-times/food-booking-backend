package in.timesinternet.foodbooking.contoller.admin;


import in.timesinternet.foodbooking.dto.request.RestaurantDto;
import in.timesinternet.foodbooking.service.RestaurantService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    BindingResultService bindingResultService;

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Object addRestaurant(@RequestBody @Valid RestaurantDto restaurantDto, BindingResult bindingResult) {
        bindingResultService.validate(bindingResult);
       return restaurantService.createRestaurant(restaurantDto);
    }

}
