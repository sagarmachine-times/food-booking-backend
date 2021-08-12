package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantDto {
    @NotNull(message = "name can't be null")
    String name;

    @NotNull(message = "subDomain can't be null")
    String subDomain;

    Date openingTime;
    Date closingTime;
    Address address;

    @NotNull(message = "email can't be null")
    @Email(message = "invalid email")
    String email;
}
