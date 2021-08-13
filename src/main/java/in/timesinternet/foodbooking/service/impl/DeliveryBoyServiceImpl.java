package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.DeliveryBoyRepository;
import in.timesinternet.foodbooking.service.DeliveryBoyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryBoyServiceImpl implements DeliveryBoyService {

    @Autowired
    DeliveryBoyRepository deliveryBoyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public DeliveryBoy createDeliveryBoy(DeliveryBoyDto deliveryBoyDto) {
        DeliveryBoy deliveryBoy = new ModelMapper().map(deliveryBoyDto, DeliveryBoy.class);
        deliveryBoy.setPassword("123");
        deliveryBoy.setPassword(passwordEncoder.encode(deliveryBoy.getPassword()));
        deliveryBoy.setRole(Role.ROLE_DELIVERY_BOY);
        return deliveryBoyRepository.save(deliveryBoy);
    }

    @Override
    public List<DeliveryBoy> getAllDeliveryBoy() {
        return null;
    }

    @Override
    public DeliveryBoy getDeliveryBoy() {
        return null;
    }

    @Override
    public DeliveryBoy deleteDeliveryBoy(Integer deliveryBoyId) {
        return null;
    }
}
