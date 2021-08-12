package in.timesinternet.foodbooking.contoller.staff;

import in.timesinternet.foodbooking.dto.request.CategoryDto;
import in.timesinternet.foodbooking.entity.Category;
import in.timesinternet.foodbooking.repository.CategoryRepository;
import in.timesinternet.foodbooking.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/staff")
public class StaffMenuController {

    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    @PreAuthorize("hasRole('ROLE_OWNER') or hasRole('ROLE_MANAGER')")
    ResponseEntity<Category> createCategory(@RequestBody  CategoryDto categoryDto, HttpServletRequest request){
        Integer restaurantId = (Integer) request.getAttribute("restaurantId");
       return ResponseEntity.ok(categoryService.createCategory(categoryDto, restaurantId));
    }

}
