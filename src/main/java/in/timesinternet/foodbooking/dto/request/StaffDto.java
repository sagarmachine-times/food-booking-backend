package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.enumeration.Role;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaffDto {
    String email;
    String firstName;
    String lastName;
    Role role;
}
