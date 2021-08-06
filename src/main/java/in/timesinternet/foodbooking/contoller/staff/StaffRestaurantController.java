package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/staff/restaurant")
public class StaffRestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping(value = "/logo")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    void updateRestaurantLogo(@RequestParam MultipartFile logo, HttpServletRequest request) {

        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        String userEmail= (String)request.getAttribute("userEmail");
        restaurantService.updateRestaurantLogo(logo, restaurantId, userEmail);
    }

    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    String test(@RequestBody MultipartFile logo, HttpServletRequest request) {
        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        String userEmail= (String)request.getAttribute("userEmail");
        return restaurantId+"  "+userEmail;
    }
}
