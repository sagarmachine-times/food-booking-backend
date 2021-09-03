package in.timesinternet.foodbooking.cache;

import in.timesinternet.foodbooking.entity.Item;

import java.util.List;

public interface ItemRepository {

    void addItem(Item item,Integer restaurantId);
    void addItemList(List<Item> item,Integer restaurantId);
    void updateItemList(List<Item> item,Integer restaurantId);
    List<Item> getItemList(Integer restaurantId);
    boolean doesExist(Integer restaurantId);
}
