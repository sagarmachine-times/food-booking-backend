package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.enumeration.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderStatusDto {

    OrderStatus orderStatus;
    Integer orderId;
}
