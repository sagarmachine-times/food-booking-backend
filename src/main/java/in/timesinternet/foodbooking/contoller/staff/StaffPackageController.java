package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.OrderStatusDto;
import in.timesinternet.foodbooking.dto.request.PackageStatusDto;
import in.timesinternet.foodbooking.entity.Package;
import in.timesinternet.foodbooking.entity.Order;
import in.timesinternet.foodbooking.service.OrderService;
import in.timesinternet.foodbooking.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/staff/order")
public class StaffPackageController {

    @Autowired
    PackageService packageService;

    @Autowired
    OrderService orderService;

//    @PutMapping("/packOrder")
//    ResponseEntity<Order> updatePackageStatus(@RequestBody OrderStatusDto orderStatusDto, HttpServletRequest request){
//        String userEmail =(String)request.getAttribute("userEmail");
//        return  ResponseEntity.ok(orderService.updateOrderStatus(orderStatusDto, userEmail));
//    }
}
