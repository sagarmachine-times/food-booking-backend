package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    @NotNull(message = "OrderId can't be null")
    Integer orderId;
    Address address;
    Contact contact;
}
