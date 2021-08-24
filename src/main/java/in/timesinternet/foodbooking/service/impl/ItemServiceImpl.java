package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.ItemDto;
import in.timesinternet.foodbooking.dto.request.ItemUpdateDto;
import in.timesinternet.foodbooking.entity.Category;
import in.timesinternet.foodbooking.entity.Item;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.exception.AlreadyExistException;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.exception.UnauthorizedException;
import in.timesinternet.foodbooking.repository.CategoryRepository;
import in.timesinternet.foodbooking.repository.ImageRepository;
import in.timesinternet.foodbooking.repository.ItemRepository;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.CategoryService;
import in.timesinternet.foodbooking.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public Item createItem(ItemDto itemDto, Integer restaurantId) {


        if (itemRepository.existsByNameAndCategoryRestaurantId(itemDto.getName(), restaurantId)) {
            throw new AlreadyExistException("Item already exist");
        } else {



            Integer categoryId = itemDto.getCategoryId();
            Integer imageId = itemDto.getImageId();
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);


            if (categoryOptional.isPresent() && restaurantOptional.isPresent()) {
                Category category = categoryOptional.get();
                Restaurant restaurant = restaurantOptional.get();



                Item item = new Item();
                item.setName(itemDto.getName());
                item.setActualPrice(itemDto.getActualPrice());
                item.setSellingPrice(itemDto.getSellingPrice());
                item.setItemType(itemDto.getItemType());

                if (imageId != null) {
                    Optional<Image> imageOptional = imageRepository.findById(imageId);
                    Image image = imageOptional.get();
                    item.setImage(image);
                }

                category.addItem(item);

                restaurant.addItem(item);

                return itemRepository.save(item);
            } else {
                throw new NotFoundException("either restaurant or category is not fouund ");
            }

        }
    }

        @Override
        public List<Item> getAllItem (Integer restaurantId){

            List<Item> allItem = itemRepository.findAllByRestaurantId(restaurantId);
            return allItem;
        }

        @Override
        public Item deleteItem (Integer itemId, Integer restaurantId){
            Optional<Item> itemOptional = itemRepository.findById(itemId);

            if (itemOptional.isPresent()) {
                Item item = itemOptional.get();

                if (item.getRestaurant().getId() == restaurantId) {
                    itemRepository.deleteById(itemId);
                    return item;
                } else {
                    throw new UnauthorizedException("unauthorized access for deleting the item");
                }
            } else {
                throw new NotFoundException("item is not found");
            }
        }


    @Override
    public Item updateItem(ItemUpdateDto itemUpdateDto, Integer restaurantId) {
        Optional<Item> itemOptional = itemRepository.findById(itemUpdateDto.getId());

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();

            if (item.getRestaurant().getId() == restaurantId) {
                if (itemUpdateDto.getName() != null)
                    item.setName(itemUpdateDto.getName());

                if (itemUpdateDto.getSellingPrice() != null)
                    item.setSellingPrice(itemUpdateDto.getSellingPrice());

                if (itemUpdateDto.getSellingPrice() != null)
                    item.setActualPrice(itemUpdateDto.getActualPrice());

                if (itemUpdateDto.getIsAvailable() != null)
                    item.setIsAvailable(itemUpdateDto.getIsAvailable());

                if (itemUpdateDto.getItemType() != null)
                    item.setItemType(itemUpdateDto.getItemType());

                if (itemUpdateDto.getCategoryId() != null) {
                    Optional<Category> categoryOptional = categoryRepository.findById(itemUpdateDto.getCategoryId());

                    if (categoryOptional.isPresent()) {
                        Category category = categoryOptional.get();
                        item.setCategory(category);
                        category.addItem(item);
                    }
                }

                if (itemUpdateDto.getImageId() != null) {
                    Optional<Image> imageOptional = imageRepository.findById(itemUpdateDto.getImageId());

                    if (imageOptional.isPresent()) {
                        Image image = imageOptional.get();
                        item.setImage(image);
                    }
                }

                itemRepository.save(item);
                return item;
            } else {
                throw new UnauthorizedException(" unauthorised access for updating the item");
            }
        } else {
            throw new NotFoundException(" item is not found");
        }
    }

    @Override
    public Item getItem(Integer itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent())
            return itemOptional.get();
        else
            throw new NotFoundException("item is not found for given id ");
    }

    @Override
    public List<Item> getItemByCategory(Integer categoryId) {
        return categoryService.getCategory(categoryId).getItemList();
    }
}
