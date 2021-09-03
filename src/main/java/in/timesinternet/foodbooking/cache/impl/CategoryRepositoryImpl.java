package in.timesinternet.foodbooking.cache.impl;

import in.timesinternet.foodbooking.cache.CategoryRepository;
import in.timesinternet.foodbooking.entity.Category;
import in.timesinternet.foodbooking.entity.Item;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    RedisTemplate redisTemplate;
    HashOperations hashOperations;

    public CategoryRepositoryImpl(RedisTemplate redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void addCategory(Category category, Integer restaurantId) {

    }

    @Override
    public void addCategoryList(List<Category> categoryList, Integer restaurantId) {
        hashOperations.put("RESTAURANT_CATEGORY", restaurantId, categoryList);
    }

    @Override
    public void updateCategoryList(List<Category> categoryList, Integer restaurantId) {
        hashOperations.put("RESTAURANT_CATEGORY", restaurantId, categoryList);

    }

    @Override
    public List<Category> getCategoryList(Integer restaurantId) {
        return (List<Category>) hashOperations.get("RESTAURANT_CATEGORY", restaurantId);

    }

    @Override
    public boolean doesExist(Integer restaurantId) {
        return hashOperations.hasKey("RESTAURANT_CATEGORY", restaurantId);
    }
}
