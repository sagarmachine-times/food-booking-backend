package in.timesinternet.foodbooking.contoller.admin;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.service.DeliveryBoyService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/delivery_boy")
public class AdminDeliveryBoyController {

    @Autowired
    DeliveryBoyService deliveryBoyService;

    @Autowired
    BindingResultService bindingResultService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<DeliveryBoy> createDeliveryBoy(@RequestBody @Valid DeliveryBoyDto deliveryBoyDto, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        return  ResponseEntity.ok(deliveryBoyService.createDeliveryBoy(deliveryBoyDto));
    }

}
