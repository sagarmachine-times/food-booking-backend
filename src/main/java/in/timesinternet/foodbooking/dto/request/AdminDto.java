package in.timesinternet.foodbooking.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminDto {
    String email;
    String password;
    String firstName;
    String lastName;
}
