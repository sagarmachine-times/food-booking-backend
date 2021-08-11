package in.timesinternet.foodbooking.contoller.customer;

import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.service.CustomerService;
import in.timesinternet.foodbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "")
    ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }


    @PostMapping(value = "/login")
    ResponseEntity<HashMap<String,String>> loginCustomer(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto.getEmail(), loginDto.getPassword()));
    }
}
