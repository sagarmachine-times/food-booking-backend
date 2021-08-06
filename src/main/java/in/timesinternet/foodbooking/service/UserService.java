package in.timesinternet.foodbooking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ResponseEntity login(String email, String password, Integer restaurantId);

}
