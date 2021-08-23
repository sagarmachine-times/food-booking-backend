package in.timesinternet.foodbooking.contoller.customer;

import in.timesinternet.foodbooking.dto.request.OrderDto;
import in.timesinternet.foodbooking.dto.request.UpdateOrderDto;
import in.timesinternet.foodbooking.entity.Order;
import in.timesinternet.foodbooking.service.OrderService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/customer/order")
public class CustomerOrderController {

    @Autowired
    BindingResultService bindingResultService;

    @Autowired
    OrderService orderService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<Order> createOrder(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        bindingResultService.validate(bindingResult);
        String userEmail =(String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(orderService.createOrder(orderDto, userEmail));
    }

    @PatchMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<Order> updateOrder(@RequestBody @Valid UpdateOrderDto updateOrderDto, BindingResult bindingResult, HttpServletRequest httpServletRequest)
    {
        bindingResultService.validate((bindingResult));
        String userEmail=(String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(orderService.updateOrder(updateOrderDto,userEmail));
    }
    @GetMapping("/getOrder")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<List<Order>> getAllOrdersOfCustomerForRestaurant(HttpServletRequest httpServletRequest)
    {
        String userEmail = (String) httpServletRequest.getAttribute("userEmail");
        return ResponseEntity.ok(orderService.getAllOrdersOfCustomerForRestaurant(userEmail));
    }

    @GetMapping("orderId")
    ResponseEntity<Order> getOrder(@PathVariable Integer orderId)
    {

        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<List<Order>> getAllOrder(HttpServletRequest request)
    {
        String userEmail = (String) request.getAttribute("userEmail");
        return  ResponseEntity.ok(orderService.getAllOrder(userEmail));
    }

}
