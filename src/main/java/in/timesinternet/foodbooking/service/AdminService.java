package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.AdminDto;
import in.timesinternet.foodbooking.entity.Admin;

import java.util.List;

public interface AdminService {

    Admin createAdmin(AdminDto adminDto);


    List<Admin> getAdmins();

}
