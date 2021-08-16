package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.AvalibilityDto;
import in.timesinternet.foodbooking.dto.request.PincodeDto;
import in.timesinternet.foodbooking.entity.Serviceability;
import in.timesinternet.foodbooking.service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/staff/pincode")
public class StaffPincodeController {

    @Autowired
    PincodeService pincodeService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Serviceability>> getPincode(HttpServletRequest request) {
        Integer resturantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(pincodeService.getPincode(resturantId));

    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Serviceability>> addPincode(@RequestBody List<PincodeDto> pincodeDto, HttpServletRequest request) {
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
    ResponseEntity<Serviceability> updatePincode(@RequestBody AvalibilityDto avalibilityDto, HttpServletRequest request) {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(pincodeService.updatePincode(avalibilityDto, restaurantId));
    }
}
