package in.timesinternet.foodbooking.contoller.delivery;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.dto.request.DeliveryBoyUpdateDto;
import in.timesinternet.foodbooking.dto.request.LoginDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.service.DeliveryBoyService;
import in.timesinternet.foodbooking.service.UserService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

}
