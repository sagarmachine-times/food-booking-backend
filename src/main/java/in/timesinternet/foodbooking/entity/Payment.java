package in.timesinternet.foodbooking.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import in.timesinternet.foodbooking.entity.enumeration.PaymentMode;
import in.timesinternet.foodbooking.entity.enumeration.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    Date createdAt;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @UpdateTimestamp
    Date updatedAt;

    @Enumerated(EnumType.STRING)
    PaymentMode mode;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    PaymentDetail paymentDetail;

}
