package in.timesinternet.foodbooking.dto.request;

import com.sun.istack.NotNull;
import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.enumeration.ItemType;
import in.timesinternet.foodbooking.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemUpdateDto {

    private Integer id;
    private String name;
    private Boolean isAvailable;
    private Integer actualPrice;
    private Integer sellingPrice;
    private ItemType itemType;
    private Integer categoryId;
    private Integer imageId;
}
