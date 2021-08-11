package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.UserRepository;
import in.timesinternet.foodbooking.security.JWTUtil;
import in.timesinternet.foodbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTUtil jwtUtil;


    @Override
    public HashMap<String, String> login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (authentication.isAuthenticated()) {
            GrantedAuthority role = authentication.getAuthorities().stream().findFirst().get();
            in.timesinternet.foodbooking.entity.User user = userRepository.findByEmail(email).get();
            String jwt ;
            if (user instanceof Staff ) {
                Staff staff= (Staff)user;

                jwt = jwtUtil.createJWT(email, role.getAuthority(),staff.getRestaurant().getId());
            }
            else if(user instanceof Customer)
            {
                Customer customer= (Customer) user;
                jwt = jwtUtil.createJWT(email, role.getAuthority(),customer.getRestaurant().getId());
            }
            else
                jwt= jwtUtil.createJWT(email, role.getAuthority(), null);

            String token = "Bearer " + jwt;
            return new HashMap<String, String>() {{
                put("token", token);
            }};
        } else
            throw new RuntimeException("invalid credentials");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        final Optional<in.timesinternet.foodbooking.entity.User> byEmail = userRepository.findByEmail(s);
        if (byEmail.isPresent()) {
            in.timesinternet.foodbooking.entity.User user = byEmail.get();
            List<GrantedAuthority> roles
                    = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority((user.getRole().toString())));
                return new User(user.getEmail(), user.getPassword(), roles);
        } else
            throw new UsernameNotFoundException("user not found");
    }
}
