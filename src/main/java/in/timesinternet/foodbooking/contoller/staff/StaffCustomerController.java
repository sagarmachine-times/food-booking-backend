package in.timesinternet.foodbooking.contoller.staff;


import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/staff/customer")
public class StaffCustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Customer>> getAllCustomer(HttpServletRequest httpServletRequest){
        Integer restaurantId=(Integer) httpServletRequest.getAttribute("restaurantId");
        return  ResponseEntity.ok(customerService.getAllCustomer(restaurantId));
    }
}
