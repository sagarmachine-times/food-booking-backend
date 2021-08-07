package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true, nullable = false, updatable = false)
    String email;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    @JsonIgnore
    String password;

    @Embedded
    Address address;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;
}
