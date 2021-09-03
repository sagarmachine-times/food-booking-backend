package in.timesinternet.foodbooking.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CODPaymentDetail extends PaymentDetail implements Serializable {
    Integer cashReceived;
}
