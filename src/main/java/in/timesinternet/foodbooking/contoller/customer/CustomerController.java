package in.timesinternet.foodbooking.contoller.customer;

import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.service.CustomerService;
import in.timesinternet.foodbooking.service.UserService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

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
    ResponseEntity<Customer> registerCustomer(@RequestBody @Valid CustomerDto customerDto){
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }


    @PostMapping(value = "/login")
    ResponseEntity<HashMap<String,String>> loginCustomer(@RequestBody LoginDto loginDto, @RequestParam Integer restaurantId){
        return ResponseEntity.ok(userService.login(loginDto.getEmail(), loginDto.getPassword(),  restaurantId));
    }
}
