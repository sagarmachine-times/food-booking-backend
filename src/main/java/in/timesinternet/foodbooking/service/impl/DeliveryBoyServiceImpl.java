package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.dto.request.PackageDeliveryDto;
import in.timesinternet.foodbooking.dto.request.DeliveryBoyUpdateDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.entity.PackageDelivery;
import in.timesinternet.foodbooking.entity.enumeration.PackageDeliveryStatus;
import in.timesinternet.foodbooking.entity.enumeration.Role;
import in.timesinternet.foodbooking.exception.InvalidRequestException;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.exception.UserAlreadyExistException;
import in.timesinternet.foodbooking.repository.DeliveryBoyRepository;
import in.timesinternet.foodbooking.repository.PackageDeliveryRepository;
import in.timesinternet.foodbooking.service.DeliveryBoyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    PackageDeliveryRepository packageDeliveryRepository;

    @Override
    public DeliveryBoy createDeliveryBoy(DeliveryBoyDto deliveryBoyDto) {
        DeliveryBoy deliveryBoy = new ModelMapper().map(deliveryBoyDto, DeliveryBoy.class);
        deliveryBoy.setPassword("123");
        deliveryBoy.setPassword(passwordEncoder.encode(deliveryBoy.getPassword()));
        deliveryBoy.setRole(Role.ROLE_DELIVERY_BOY);

        try
        {
            return deliveryBoyRepository.save(deliveryBoy);
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            throw new UserAlreadyExistException("delivery boy with the given email "+deliveryBoyDto.getEmail()+" already exits");
        }
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
            throw new NotFoundException("DeliveryBoy with Id: "+deliveryBoyId+" is not found");
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
            throw new NotFoundException("DeliveryBoy with Id: "+deliveryBoyId+" is not found");
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
            throw new NotFoundException("DeliveryBoy with Id: "+deliveryBoyId+" is not found");
        }

    }

    @Override
    public DeliveryBoy getDeliveryBoy(String userEmail)
    {
        Optional<DeliveryBoy> deliveryBoyOptional = deliveryBoyRepository.findByEmail(userEmail);

        if (deliveryBoyOptional.isPresent())
        {
            return deliveryBoyOptional.get();
        }
        else
        {
            throw new NotFoundException(" delivery boy with given email "+userEmail+" not found");
        }
    }

    @Override
    public PackageDelivery updatePackageDelivery(PackageDeliveryDto packageDeliveryDto, String userEmail)
    {
        DeliveryBoy deliveryBoy = getDeliveryBoy(userEmail);
        Optional<PackageDelivery> packageDeliveryOptional = packageDeliveryRepository.findById(packageDeliveryDto.getPackageDeliveryId());

        if (packageDeliveryOptional.isPresent())
        {
            PackageDelivery packageDelivery = packageDeliveryOptional.get();

            switch (packageDeliveryDto.getPackageDeliveryStatus())
            {
                case ON_THE_WAY_TO_PICK:
                    return onTheWayToPickPackage(packageDelivery);
                case PICKED:
                    return pickedThePackage(packageDelivery);
                case ON_THE_WAY_TO_DROP:
                    return onTheWayToDropPackage(packageDelivery);
                case DELIVERED:
                    return deliveredThePackage(packageDelivery);
                case CANCELED:
                    return canceledThePackage(packageDelivery);
                default:
                    throw new NotFoundException("package not found");
            }
        }
        else
        {
            throw  new NotFoundException("given package with package id - "+packageDeliveryDto.getPackageDeliveryId()+" not found");
        }

    }

    PackageDelivery onTheWayToPickPackage(PackageDelivery packageDelivery)
    {


        if (!packageDelivery.getStatus().equals(PackageDeliveryStatus.ASSIGNED))
            throw new InvalidRequestException("invalid request for way to pickup ");
        packageDelivery.setStatus(PackageDeliveryStatus.ON_THE_WAY_TO_PICK);

        return packageDeliveryRepository.save(packageDelivery);
    }

    PackageDelivery pickedThePackage(PackageDelivery packageDelivery)
    {


        if (packageDelivery.getStatus().equals(PackageDeliveryStatus.ASSIGNED) || packageDelivery.getStatus().equals(PackageDeliveryStatus.ON_THE_WAY_TO_PICK) ) {
            packageDelivery.setStatus(PackageDeliveryStatus.PICKED);
            return packageDeliveryRepository.save(packageDelivery);
        }
        else
        {
            throw new InvalidRequestException("invalid request for picking");
        }
    }

    PackageDelivery onTheWayToDropPackage(PackageDelivery packageDelivery)
    {


        if (!packageDelivery.getStatus().equals(PackageDeliveryStatus.PICKED))
            throw new InvalidRequestException("invalid request for dropping");

        packageDelivery.setStatus(PackageDeliveryStatus.ON_THE_WAY_TO_DROP);

        return packageDeliveryRepository.save(packageDelivery);
    }

    PackageDelivery deliveredThePackage(PackageDelivery packageDelivery)
    {
        if (packageDelivery.getStatus().equals(PackageDeliveryStatus.PICKED) || packageDelivery.getStatus().equals(PackageDeliveryStatus.ON_THE_WAY_TO_DROP) ) {
            packageDelivery.setStatus(PackageDeliveryStatus.DELIVERED);
            return packageDeliveryRepository.save(packageDelivery);
        }
        else
        {
            throw new InvalidRequestException("invalid request for delivering");
        }
    }

    PackageDelivery canceledThePackage(PackageDelivery packageDelivery)
    {
        if (packageDelivery.getStatus().equals(PackageDeliveryStatus.DELIVERED))
            throw new InvalidRequestException("invalid request for cancelling");

        packageDelivery.setStatus(PackageDeliveryStatus.CANCELED);

        return packageDeliveryRepository.save(packageDelivery);
    }

}
