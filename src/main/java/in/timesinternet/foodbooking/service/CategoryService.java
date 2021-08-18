package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CategoryDto;
import in.timesinternet.foodbooking.dto.request.CategoryUpdateDto;
import in.timesinternet.foodbooking.entity.Category;
import java.util.*;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto, Integer restaurantId);

    List<Category> getAllCategory(Integer restaurantId);

    Category deleteCategory(Integer categoryId, Integer restaurantId);

    Category updateCategory(CategoryUpdateDto categoryUpdateDto, Integer restaurantId);

    Category getCategory(Integer categoryId);
}
