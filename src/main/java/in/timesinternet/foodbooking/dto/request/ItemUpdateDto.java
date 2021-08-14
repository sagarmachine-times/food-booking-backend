package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.enumeration.ItemType;
import in.timesinternet.foodbooking.entity.Category;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemUpdateDto {

    @NotNull(message = "name can't be null")
    private Integer id;

    private String name;
    private Boolean isAvailable;
    private Integer actualPrice;
    private Integer sellingPrice;
    private ItemType itemType;
    private Integer categoryId;
    private Integer imageId;
}
