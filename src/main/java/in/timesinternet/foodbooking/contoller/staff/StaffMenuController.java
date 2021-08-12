package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.CategoryDto;
import in.timesinternet.foodbooking.dto.request.CategoryUpdateDto;
import in.timesinternet.foodbooking.entity.Category;
import in.timesinternet.foodbooking.repository.CategoryRepository;
import in.timesinternet.foodbooking.service.CategoryService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffMenuController {

    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    CategoryService categoryService;

    @Autowired
    BindingResultService bindingResultService;

    @PostMapping("/category")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDto categoryDto, HttpServletRequest request, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
       return ResponseEntity.ok(categoryService.createCategory(categoryDto, restaurantId));
    }

    @GetMapping("/category")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<List<Category>> getAllCategory(HttpServletRequest request)
    {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(categoryService.getAllCategory(restaurantId));
    }

    @DeleteMapping("/category/{categoryId}")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Category> deleteCategory(@PathVariable Integer categoryId, HttpServletRequest request)
    {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId, restaurantId));
    }

    @PatchMapping("/category")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Category> updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto, HttpServletRequest request)
    {
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
        return ResponseEntity.ok(categoryService.updateCategory( categoryUpdateDto, restaurantId));
    }

}
