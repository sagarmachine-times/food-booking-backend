package in.timesinternet.foodbooking.contoller.admin;


import in.timesinternet.foodbooking.dto.request.RestaurantDto;
import in.timesinternet.foodbooking.dto.request.RestaurantUpdateDto;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.service.RestaurantService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
     @PatchMapping("/{restaurantId}")
     @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Restaurant> updateRestaurant(@RequestBody @Valid RestaurantUpdateDto restaurantUpdateDto,BindingResult bindingResult, @PathVariable Integer restaurantId)
     {
         bindingResultService.validate(bindingResult);
         return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantUpdateDto,restaurantId));
     }
    @PatchMapping(value = "/logo/{restaurantId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateRestaurantLogo(@RequestParam MultipartFile logo,@PathVariable Integer restaurantId) {
        restaurantService.updateRestaurantLogo(logo, restaurantId);
    }

    @GetMapping(value = "")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Restaurant> getAllRestaurant() {
        return restaurantService.getAllRestaurant();
    }

}
