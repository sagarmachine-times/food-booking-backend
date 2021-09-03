package in.timesinternet.foodbooking.cache;

import in.timesinternet.foodbooking.entity.Category;

import java.util.List;

public interface CategoryRepository {
    void addCategory(Category category, Integer restaurantId);
    void addCategoryList(List<Category> categoryList, Integer restaurantId);
    void updateCategoryList(List<Category> categoryList,Integer restaurantId);
    List<Category> getCategoryList(Integer restaurantId);
    boolean doesExist(Integer restaurantId);
}
