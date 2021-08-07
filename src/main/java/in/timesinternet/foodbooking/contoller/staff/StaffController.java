package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.dto.request.StaffDto;
import in.timesinternet.foodbooking.dto.request.StaffUpdateDto;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.service.StaffService;
import in.timesinternet.foodbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/staff")
public class StaffController {
    @Autowired
    UserService userService;

    @Autowired
    StaffService staffService;

    @PostMapping(value = "/login")
    ResponseEntity login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto.getEmail(), loginDto.getPassword());
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    ResponseEntity<List<Staff>> createStaff(@RequestBody StaffDto staffDto, HttpServletRequest request) {
        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        return ResponseEntity.ok(staffService.createStaff(restaurantId, staffDto));
    }

    @GetMapping(value = "")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    ResponseEntity<List<Staff>> getAllStaff(HttpServletRequest request){
        Integer restaurantId=(Integer)request.getAttribute("restaurantId");
        return ResponseEntity.ok(staffService.getStaff(restaurantId));
    }

    @PatchMapping(value = "")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Staff> updateStaff(@RequestBody StaffUpdateDto staffUpdateDto, HttpServletRequest request){
        String userEmail=(String) request.getAttribute("userEmail") ;
        return  ResponseEntity.ok(staffService.updateStaff(userEmail, staffUpdateDto));
    }

    @DeleteMapping("/{staffId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    ResponseEntity<Staff> deleteStaff(@PathVariable Integer staffId,HttpServletRequest request){
        String userEmail=(String) request.getAttribute("userEmail") ;
        return  ResponseEntity.ok(staffService.deleteStaff(userEmail, staffId));
    }



}
