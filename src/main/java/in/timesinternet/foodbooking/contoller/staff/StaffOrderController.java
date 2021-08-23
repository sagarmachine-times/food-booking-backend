package in.timesinternet.foodbooking.contoller.staff;
;
import in.timesinternet.foodbooking.dto.request.OrderStatusDto;
import in.timesinternet.foodbooking.entity.Order;
import in.timesinternet.foodbooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/staff/order")
public class StaffOrderController {

    @Autowired
    OrderService orderService;

    @PatchMapping("/status")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Order> updateOrderStatus(@RequestBody OrderStatusDto orderStatusDto, HttpServletRequest request){
        String userEmail =(String)request.getAttribute("userEmail");
        return  ResponseEntity.ok(orderService.updateOrderStatus(orderStatusDto, userEmail));
    }

}
