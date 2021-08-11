package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.entity.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.List;

public interface UserService extends UserDetailsService {

    HashMap<String,String> login(String email, String password);

}
