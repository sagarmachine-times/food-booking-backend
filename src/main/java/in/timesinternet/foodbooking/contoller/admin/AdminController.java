package in.timesinternet.foodbooking.contoller.admin;

import in.timesinternet.foodbooking.dto.request.AdminDto;
import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.entity.Admin;
import in.timesinternet.foodbooking.exception.InvalidRequestBodyException;
import in.timesinternet.foodbooking.repository.AdminRepository;
import in.timesinternet.foodbooking.service.AdminService;
import in.timesinternet.foodbooking.service.UserService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    BindingResultService bindingResultService;


    @ApiOperation(value = "create admin", notes = "restricted")
    @PostMapping(value = "", consumes = {"application/json"})
//    @PreAuthorize("hasRole('ROLE_SUPER')")
    Admin createAdmin(@RequestBody @Valid AdminDto adminDto, BindingResult bindingResult) {
        bindingResultService.validate(bindingResult);
        return adminService.createAdmin(adminDto);
    }

    @ApiOperation(value = "get all admins", notes = "role - ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "")
    List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    @ApiOperation(value = "admin login", notes = "response - jwt token ")
    @PostMapping(value = "/login")
    ResponseEntity<HashMap<String, String>> login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {
        bindingResultService.validate(bindingResult);
        return ResponseEntity.ok(userService.login(loginDto.getEmail(), loginDto.getPassword()));
    }

}
