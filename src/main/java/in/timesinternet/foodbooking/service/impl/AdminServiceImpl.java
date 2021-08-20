package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.AdminDto;
import in.timesinternet.foodbooking.entity.Admin;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.exception.UserAlreadyExistException;
import in.timesinternet.foodbooking.repository.AdminRepository;
import in.timesinternet.foodbooking.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Admin createAdmin(AdminDto adminDto) {
        ModelMapper modelMapper= new ModelMapper();
        Admin admin = modelMapper.map(adminDto, Admin.class);
        admin.setRole(Role.ROLE_ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        try
        {
            return adminRepository.save(admin);
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            throw new UserAlreadyExistException("User with email :- "+admin.getEmail()+" already exists");
        }
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
}
