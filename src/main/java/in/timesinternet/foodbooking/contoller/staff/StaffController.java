package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.dto.request.StaffDto;
import in.timesinternet.foodbooking.dto.request.StaffUpdateDto;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.service.StaffService;
import in.timesinternet.foodbooking.service.UserService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/staff")
public class StaffController {
    @Autowired
    UserService userService;

    @Autowired
    StaffService staffService;

    @Autowired
    BindingResultService bindingResultService;

    @PostMapping(value = "/login")
    ResponseEntity<HashMap<String,String>> login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        return ResponseEntity.ok(userService.login(loginDto.getEmail(), loginDto.getPassword()));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    ResponseEntity<List<Staff>> createStaff(@RequestBody @Valid StaffDto staffDto, HttpServletRequest request, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
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
    ResponseEntity<Staff> updateStaff(@RequestBody @Valid StaffUpdateDto staffUpdateDto, HttpServletRequest request, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        String userEmail=(String) request.getAttribute("userEmail") ;
        return  ResponseEntity.ok(staffService.updateStaff(userEmail, staffUpdateDto));
    }

    @DeleteMapping("/{staffId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    ResponseEntity<Staff> deleteStaff(@PathVariable @Valid Integer staffId,HttpServletRequest request, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        String userEmail=(String) request.getAttribute("userEmail") ;
        return  ResponseEntity.ok(staffService.deleteStaff(userEmail, staffId));
    }



}
