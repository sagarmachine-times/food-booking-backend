package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CategoryDto;
import in.timesinternet.foodbooking.entity.Category;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto, Integer restaurantId);

}
