package in.timesinternet.foodbooking.contoller.admin;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.service.DeliveryBoyService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<List<DeliveryBoy>>getAllDeliveryBoy()
    {
        return ResponseEntity.ok(deliveryBoyService.getAllDeliveryBoy());
    }
    @DeleteMapping("/{deliveryBoyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<DeliveryBoy>deleteDeliveryBoy(@PathVariable Integer deliveryBoyId)
    {
        return ResponseEntity.ok(deliveryBoyService.deleteDeliveryBoy(deliveryBoyId));
    }

}
