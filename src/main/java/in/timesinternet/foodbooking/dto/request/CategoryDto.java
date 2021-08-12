package in.timesinternet.foodbooking.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {

    @NotNull(message = "name can't be null")
    private String name;

}
