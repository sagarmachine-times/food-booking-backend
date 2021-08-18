package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.enumeration.ItemType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {

    @NotNull(message = "name can't be null")
    private String name;

    @NotNull(message = "value can't be null")
    private Integer actualPrice;

    @NotNull(message =  "value can't be null")
    private Integer sellingPrice;

    @NotNull(message = "type can't be null")
    @Enumerated(EnumType.STRING)
    ItemType itemType;

    @NotNull(message = "type can't be null")
    Integer categoryId;

    Integer imageId;

}