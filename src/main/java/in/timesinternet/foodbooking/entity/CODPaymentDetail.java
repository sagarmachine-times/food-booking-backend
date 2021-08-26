package in.timesinternet.foodbooking.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CODPaymentDetail extends PaymentDetail {
    Integer cashReceived;
}
