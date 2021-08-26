package in.timesinternet.foodbooking.contoller.customer;

import in.timesinternet.foodbooking.dto.request.*;
import in.timesinternet.foodbooking.entity.Coupon;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.AddressContact;
import in.timesinternet.foodbooking.service.CustomerService;
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
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BindingResultService bindingResultService;

    @PostMapping(value = "")
    ResponseEntity<Customer> registerCustomer(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }


    @PostMapping(value = "/login")
    ResponseEntity<HashMap<String,Object>> loginCustomer(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult,
                                                         @RequestParam Integer restaurantId){

        bindingResultService.validate(bindingResult);
        return ResponseEntity.ok(userService.login(loginDto.getEmail(), loginDto.getPassword(),  restaurantId));
    }

    @GetMapping(value="/restaurant/{restaurantId}")
    ResponseEntity<RestaurantResponseDto> getRestaurantDetail(@PathVariable Integer restaurantId)
    {
        return ResponseEntity.ok(customerService.getRestaurantDetail(restaurantId));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<Customer> getCustomer(HttpServletRequest httpServletRequest){
        String userEmail =(String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(customerService.getCustomer(userEmail));
    }

    @PutMapping(value="/updateProfile")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<Customer> updateCustomerProfile(@RequestBody @Valid CustomerUpdateDto customerUpdateDto, BindingResult bindingResult,
                                                   HttpServletRequest httpServletRequest)
    {
        bindingResultService.validate(bindingResult);
        String userEmail =(String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(customerService.updateCustomerProfile(customerUpdateDto, userEmail));
    }

    @PostMapping(value = "/address")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<List<AddressContact>> addAddress(@RequestBody @Valid AddressContact address,BindingResult bindingResult, HttpServletRequest httpServletRequest){
        bindingResultService.validate(bindingResult);
        String userEmail =(String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(customerService.addAddress(address, userEmail));
    }

    @GetMapping(value = "/address")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<List<AddressContact>> getAddresses(HttpServletRequest httpServletRequest){
        String userEmail =(String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(customerService.getAddresses(userEmail));
    }

}
