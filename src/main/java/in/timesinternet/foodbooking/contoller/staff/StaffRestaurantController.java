package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.*;
import in.timesinternet.foodbooking.entity.Coupon;
import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Serviceability;
import in.timesinternet.foodbooking.repository.ImageRepository;
import in.timesinternet.foodbooking.service.CouponService;
import in.timesinternet.foodbooking.service.RestaurantService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import in.timesinternet.foodbooking.service.PincodeService;

import in.timesinternet.foodbooking.util.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/staff/restaurant")
public class StaffRestaurantController {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    CouponService couponService;
    @Autowired
    BindingResultService bindingResultService;
    @Autowired
    PincodeService pincodeService;


    @PatchMapping(value = "/logo")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    void updateRestaurantLogo(@RequestParam MultipartFile logo, HttpServletRequest request) {

        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        restaurantService.updateRestaurantLogo(logo, restaurantId);
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Restaurant> getRestaurant(HttpServletRequest request) {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(restaurantService.getRestaurant(restaurantId));
    }

    @PatchMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Restaurant> updateRestaurant(@RequestBody @Valid RestaurantUpdateDto restaurantUpdateDto, BindingResult bindingResult, HttpServletRequest request) {
        bindingResultService.validate(bindingResult);
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantUpdateDto, restaurantId));
    }
}