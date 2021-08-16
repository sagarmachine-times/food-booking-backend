package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    @Email(message = "invalid email")
    @NotNull(message = "email can't be null")
    String email;
    @NotNull(message = "password can't be null")
    String password;

    @NotNull(message = "firstName can't be null")
    String firstName;

    @NotNull(message = "lastName can't be null")
    String lastName;

    Address address;
    Contact contact;
    @NotNull(message = "restaurantId can't be null")
    Integer restaurantId;
}
