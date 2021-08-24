package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.AvalibilityDto;
import in.timesinternet.foodbooking.dto.request.PincodeDto;
import in.timesinternet.foodbooking.entity.Serviceability;
import in.timesinternet.foodbooking.service.PincodeService;
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
@RequestMapping("/api/staff/pincode")
public class StaffPincodeController {

    @Autowired
    PincodeService pincodeService;

    @Autowired
    BindingResultService bindingResultService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Serviceability>> getPincode(HttpServletRequest request) {
        Integer resturantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(pincodeService.getPincodeList(resturantId));

    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Serviceability>> addPincode(@RequestBody @Valid List<PincodeDto> pincodeDto,BindingResult bindingResult, HttpServletRequest request) {
        bindingResultService.validate(bindingResult);
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(pincodeService.addPincode(pincodeDto, restaurantId));
    }


    @DeleteMapping("/{pincodeId}")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Serviceability> deletePincode(@PathVariable Integer pincodeId, HttpServletRequest request) {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");

        return ResponseEntity.ok(pincodeService.deletePincode(pincodeId, restaurantId));
    }

    @PatchMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Serviceability> updatePincode(@RequestBody @Valid AvalibilityDto avalibilityDto, BindingResult bindingResult,HttpServletRequest request) {
        bindingResultService.validate(bindingResult);
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(pincodeService.updatePincode(avalibilityDto, restaurantId));
    }
}
