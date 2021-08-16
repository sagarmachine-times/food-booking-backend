package in.timesinternet.foodbooking.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryUpdateDto {

    private Integer id;
    private String name;
    private Boolean isAvailable;

}
