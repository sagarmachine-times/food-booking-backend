package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerUpdateDto {

//    Integer id;

//    Integer restaurantId;

//    @NotNull(message = "actual email can't be null")
//    String actualEmail;

//    String email;

    String password;

//  @NotNull(message = "firstName can't be null")
    String firstName;

//    @NotNull(message = "lastName can't be null")
    String lastName;


    Address address;
    Contact contact;
}
