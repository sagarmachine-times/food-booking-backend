package in.timesinternet.foodbooking.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminDto {
    @Email(message = "invalid email")
    @NotNull(message = "email can't be null")
    String email;

    @NotNull(message = "password can't be null")
    String password;

    @NotNull(message = "firstName can't be null")
    String firstName;

    @NotNull(message = "lastName can't be null")
    String lastName;
}
