package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.dto.request.StaffDto;
import in.timesinternet.foodbooking.service.StaffService;
import in.timesinternet.foodbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/staff")
public class StaffController {
    @Autowired
    UserService userService;

    @Autowired
    StaffService staffService;

    @PostMapping(value = "/{restaurantId}/login")
    ResponseEntity login(@RequestBody LoginDto loginDto, @PathVariable Integer restaurantId){
        return userService.login(loginDto.getEmail(), loginDto.getPassword(), restaurantId);
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    Object createStaff(@RequestBody StaffDto staffDto, HttpServletRequest request) {
        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        return staffService.createStaff(restaurantId, staffDto);
    }


}
