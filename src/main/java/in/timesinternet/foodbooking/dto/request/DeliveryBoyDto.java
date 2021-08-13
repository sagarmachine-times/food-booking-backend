package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeliveryBoyDto {

    @NotNull(message = "email can't be null")
    @Email(message = "invalid email")
    String email;

    @NotNull(message = "first name can't be null")
    String firstName;

    @NotNull(message = "last name can't be null")
    String lastName;

    Address address;
    Contact contact;

}
