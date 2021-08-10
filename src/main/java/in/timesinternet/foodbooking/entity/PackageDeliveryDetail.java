package in.timesinternet.foodbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDeliveryDetail {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Integer id;
}
