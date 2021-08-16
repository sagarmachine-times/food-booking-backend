package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.dto.request.DeliveryBoyUpdateDto;
import in.timesinternet.foodbooking.entity.Coupon;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.repository.DeliveryBoyRepository;
import in.timesinternet.foodbooking.service.DeliveryBoyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
       return deliveryBoyRepository.findAll();
    }

    @Override
    public DeliveryBoy getDeliveryBoy(Integer deliveryBoyId) {
        Optional<DeliveryBoy> optionalDeliveryBoy = deliveryBoyRepository.findById(deliveryBoyId);
        if(optionalDeliveryBoy.isPresent())
        {
            DeliveryBoy deliveryBoy=optionalDeliveryBoy.get();
            return deliveryBoy;
        }
        else {
            throw new RuntimeException("DeliveryBoy with Id: "+deliveryBoyId+" Does not Exist");
        }
    }

    @Override
    public DeliveryBoy deleteDeliveryBoy(Integer deliveryBoyId) {
        Optional<DeliveryBoy> optionalDeliveryBoy = deliveryBoyRepository.findById(deliveryBoyId);
        if(optionalDeliveryBoy.isPresent())
        {
            DeliveryBoy deliveryBoy=optionalDeliveryBoy.get();
            deliveryBoyRepository.delete(deliveryBoy);
            return deliveryBoy;
        }
        else {
            throw new RuntimeException("DeliveryBoy with Id: "+deliveryBoyId+" Does not Exist");
        }
    }
    @Override
    public  DeliveryBoy UpdateDeliveryBoy(DeliveryBoyUpdateDto deliveryBoyUpdateDto, Integer deliveryBoyId)
    {
        Optional<DeliveryBoy> deliveryBoyOptional = deliveryBoyRepository.findById(deliveryBoyId);
        if(deliveryBoyOptional.isPresent())
        {
            DeliveryBoy deliveryBoy=deliveryBoyOptional.get();
            ModelMapper modelMapper=new ModelMapper();

           modelMapper.map(deliveryBoyUpdateDto,deliveryBoy);
            deliveryBoyRepository.save(deliveryBoy);
            return deliveryBoy;

        }
        else {
            throw new RuntimeException("This deliveryBoy does not exist");
        }

    }
}
