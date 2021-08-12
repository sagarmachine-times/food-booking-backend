package in.timesinternet.foodbooking.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AvalibilityDto {
    int id;
    Boolean isAvailable;
}
