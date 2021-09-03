package in.timesinternet.foodbooking.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class ImageDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

}
