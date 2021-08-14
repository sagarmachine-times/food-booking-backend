package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.ItemDto;
import in.timesinternet.foodbooking.entity.Item;

public interface ItemService {

    Item createItem(ItemDto itemDto, Integer restaurantId);
}
