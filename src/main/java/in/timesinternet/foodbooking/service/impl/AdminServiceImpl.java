package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.AdminDto;
import in.timesinternet.foodbooking.entity.Admin;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.AdminRepository;
import in.timesinternet.foodbooking.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return  adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
}
