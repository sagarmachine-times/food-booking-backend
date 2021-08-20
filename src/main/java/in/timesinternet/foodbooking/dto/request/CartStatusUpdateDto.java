package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.enumeration.CartStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartStatusUpdateDto {
    CartStatus status;
}
