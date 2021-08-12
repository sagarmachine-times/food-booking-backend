package in.timesinternet.foodbooking.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @Email(message = "invalid email")
    @NotNull(message = "email can't be null")
    String email;

    @NotNull(message = "password can't be null")
    String password;
}
