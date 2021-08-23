package in.timesinternet.foodbooking.contoller.delivery;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyUpdateDto;
import in.timesinternet.foodbooking.dto.request.PackageDeliveryDto;
import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.entity.PackageDelivery;
import in.timesinternet.foodbooking.service.DeliveryBoyService;
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

@RestController
@RequestMapping("/api/delivery_boy")
public class DeliveryBoyController {

    @Autowired
    BindingResultService bindingResultService;

    @Autowired
    DeliveryBoyService deliveryBoyService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Object>> loginDeliveryBoy(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        return  ResponseEntity.ok(userService.login(loginDto.getEmail(), loginDto.getPassword()));
    }
    @PatchMapping("/{deliveryBoyId}")
    public ResponseEntity<DeliveryBoy>UpdateDeliveryBoy(@RequestBody @Valid DeliveryBoyUpdateDto deliveryBoyUpdateDto, @PathVariable Integer deliveryBoyId,BindingResult bindingResult)
    {
        bindingResultService.validate(bindingResult);
        return ResponseEntity.ok(deliveryBoyService.UpdateDeliveryBoy(deliveryBoyUpdateDto,deliveryBoyId));
    }

    @PutMapping("/updatePackDelivery")
    @PreAuthorize("hasRole('ROLE_DELIVERY_BOY') ")
    public ResponseEntity<PackageDelivery> updatePackageDelivery(@RequestBody @Valid PackageDeliveryDto packageDeliveryDto,
                                                                 BindingResult bindingResult, HttpServletRequest request)
    {
        bindingResultService.validate(bindingResult);
        String userEmail = (String) request.getAttribute("userEmail");
        return ResponseEntity.ok(deliveryBoyService.updatePackageDelivery(packageDeliveryDto, userEmail));
    }

}
