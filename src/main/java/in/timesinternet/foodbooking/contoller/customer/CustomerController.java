package in.timesinternet.foodbooking.contoller.customer;

import in.timesinternet.foodbooking.dto.request.CategoryUpdateDto;
import in.timesinternet.foodbooking.dto.request.CustomerDto;
import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.dto.request.RestaurantResponseDto;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.service.CustomerService;
import in.timesinternet.foodbooking.service.UserService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<Customer> registerCustomer(@RequestBody @Valid CustomerDto customerDto){
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }


    @PostMapping(value = "/login")
    ResponseEntity<HashMap<String,String>> loginCustomer(@RequestBody LoginDto loginDto, @RequestParam Integer restaurantId){
        return ResponseEntity.ok(userService.login(loginDto.getEmail(), loginDto.getPassword(),  restaurantId));
    }

    @GetMapping(value="/restaurant/{subDomain}")
    ResponseEntity<RestaurantResponseDto> getRestaurantDetail(@PathVariable String subDomain)
    {
        return ResponseEntity.ok(customerService.getRestaurantDetail(subDomain));
    }

    @GetMapping(value="/restaurant/category/{restaurantId}")
    ResponseEntity<List<CategoryUpdateDto>> getRestaurantCategory(@PathVariable Integer restaurantId)
    {
        return ResponseEntity.ok(customerService.getRestaurantCategory(restaurantId));
    }


}
