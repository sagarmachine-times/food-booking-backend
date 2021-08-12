package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaffDto {
    @Email(message = "invalid email")
    @NotNull(message = "email can't be null")
    String email;
    @NotNull(message = "firstName can't be null")
    String firstName;
    @NotNull(message = "lastName can't be null")
    String lastName;
    @NotNull(message = "role can't be null")
    Role role;
    Address address;
}
