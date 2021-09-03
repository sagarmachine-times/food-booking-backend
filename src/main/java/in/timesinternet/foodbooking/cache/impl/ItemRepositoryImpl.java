package in.timesinternet.foodbooking.cache.impl;

import in.timesinternet.foodbooking.cache.ItemRepository;
import in.timesinternet.foodbooking.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    RedisTemplate redisTemplate;
    HashOperations hashOperations;
    public ItemRepositoryImpl(RedisTemplate redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void addItem(Item item, Integer restaurantId) {

    }

    @Override
    public void addItemList(List<Item> itemList, Integer restaurantId) {
        hashOperations.put("RESTAURANT_ITEM", restaurantId, itemList);
    }

    @Override
    public void updateItemList(List<Item> itemList, Integer restaurantId) {
        hashOperations.put("RESTAURANT_ITEM", restaurantId, itemList);
    }

    @Override
    public List<Item> getItemList(Integer restaurantId) {
        return (List<Item>) hashOperations.get("RESTAURANT_ITEM", restaurantId);
    }

    @Override
    public boolean doesExist(Integer restaurantId) {
        return hashOperations.hasKey("RESTAURANT_ITEM", restaurantId);
    }
}
