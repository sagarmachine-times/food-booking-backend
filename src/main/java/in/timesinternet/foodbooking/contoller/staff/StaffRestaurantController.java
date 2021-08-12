package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.contoller.dto.request.PincodeDto;
import in.timesinternet.foodbooking.dto.request.AvalibilityDto;
import in.timesinternet.foodbooking.dto.request.RestaurantUpdateDto;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Serviceability;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.service.RestaurantService;
import in.timesinternet.foodbooking.service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/staff/restaurant")
public class  StaffRestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PatchMapping(value = "/logo")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    void updateRestaurantLogo(@RequestParam MultipartFile logo, HttpServletRequest request) {

        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        String userEmail= (String)request.getAttribute("userEmail");
        restaurantService.updateRestaurantLogo(logo, restaurantId, userEmail);
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Restaurant> getRestaurant(HttpServletRequest request){
        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        return  ResponseEntity.ok(restaurantService.getRestaurant(restaurantId));
    }

    @PatchMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Restaurant> updateRestaurant(@RequestBody RestaurantUpdateDto restaurantUpdateDto, HttpServletRequest request){
        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        return  ResponseEntity.ok(restaurantService.updateRestaurant(restaurantUpdateDto,restaurantId));
    }

    @Autowired
    PincodeService pincodeService;
//    @PostMapping("/pincode")
//    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
//    ResponseEntity<Serviceability> addPincode(@RequestBody PincodeDto pincodeDto, HttpServletRequest request){
//        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
//
//        return ResponseEntity.ok(pincodeService.addPincode(pincodeDto, restaurantId));
//    }
    @GetMapping("/pincode")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Serviceability>> getPincode(HttpServletRequest request)
    {
        Integer resturantId=(Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(pincodeService.getPincode(resturantId));

    }

    @PostMapping("/pincode")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Serviceability>> addPincode(@RequestBody List<PincodeDto>pincodeDto, HttpServletRequest request){
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");

        return ResponseEntity.ok(pincodeService.addPincode(pincodeDto, restaurantId));
    }


    @DeleteMapping("/pincode/{pincodeId}")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Serviceability> deletePincode(@PathVariable Integer pincodeId, HttpServletRequest request){
        Integer restaurantId=(Integer) request.getAttribute("restaurantId");

        return  ResponseEntity.ok(pincodeService.deletePincode(pincodeId, restaurantId));
    }

    @PatchMapping("/pincode")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Serviceability> updatePincode(@RequestBody AvalibilityDto avalibilityDto,HttpServletRequest request)
    {
        Integer restaurantId=(Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(pincodeService.updatePincode(avalibilityDto,restaurantId));
    }
}
