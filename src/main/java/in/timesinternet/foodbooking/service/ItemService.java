package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.ItemDto;
import in.timesinternet.foodbooking.dto.request.ItemUpdateDto;
import in.timesinternet.foodbooking.entity.Item;
import io.swagger.models.auth.In;

import java.util.List;

public interface ItemService {

    Item createItem(ItemDto itemDto, Integer restaurantId);

    List<Item> getAllItem(Integer restaurantId);

    Item deleteItem(Integer itemId, Integer restaurantId);

    Item updateItem(ItemUpdateDto itemUpdateDto, Integer restaurantId);

    Item getItem(Integer itemId);

    List<Item> getItemByCategory(Integer categoryId);
}
