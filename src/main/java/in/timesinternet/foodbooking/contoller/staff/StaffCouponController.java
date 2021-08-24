package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.AvalibilityDto;
import in.timesinternet.foodbooking.dto.request.CouponDto;
import in.timesinternet.foodbooking.dto.request.PincodeDto;
import in.timesinternet.foodbooking.dto.request.UpdateCouponDto;
import in.timesinternet.foodbooking.entity.Coupon;
import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.Serviceability;
import in.timesinternet.foodbooking.repository.ImageRepository;
import in.timesinternet.foodbooking.service.CouponService;
import in.timesinternet.foodbooking.service.PincodeService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import in.timesinternet.foodbooking.util.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/staff/coupon")
public class StaffCouponController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    CouponService couponService;

    @Autowired
    BindingResultService bindingResultService;

    @PostMapping("/image")
    ResponseEntity<Image> uploadImage(@RequestParam MultipartFile couponImage) {
        return ResponseEntity.ok(imageRepository.save(imageService.uploadImage(couponImage)));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Coupon> addCoupon(@RequestBody @Valid CouponDto couponDto,BindingResult bindingResult,HttpServletRequest request) {
        bindingResultService.validate(bindingResult);
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(couponService.addCoupon(couponDto, restaurantId));
    }

    @PatchMapping("/{couponId}")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Coupon> updateCoupon(@RequestBody @Valid UpdateCouponDto updateCouponDto,BindingResult bindingResult, @PathVariable Integer couponId,
                                        HttpServletRequest request) {
        bindingResultService.validate(bindingResult);
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(couponService.updateCoupon(updateCouponDto, couponId, restaurantId));
    }

    @DeleteMapping("/{couponId}")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Coupon> deleteCoupon(@PathVariable Integer couponId, HttpServletRequest request) {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(couponService.deleteCoupon(couponId, restaurantId));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Coupon>> getAllCoupon(HttpServletRequest request) {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(couponService.getAllCoupon(restaurantId));
    }}
