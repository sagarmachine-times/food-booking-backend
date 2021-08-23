package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.dto.request.PackageDeliveryDto;
import in.timesinternet.foodbooking.dto.request.DeliveryBoyUpdateDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;
import in.timesinternet.foodbooking.entity.PackageDelivery;

import java.util.List;

public interface DeliveryBoyService {

    DeliveryBoy createDeliveryBoy(DeliveryBoyDto deliveryBoyDto);
    List<DeliveryBoy> getAllDeliveryBoy();

    DeliveryBoy getDeliveryBoy(Integer deliveryBoyId);

    DeliveryBoy deleteDeliveryBoy(Integer deliveryBoyId);

    DeliveryBoy getDeliveryBoy(String userEmail);


    DeliveryBoy UpdateDeliveryBoy(DeliveryBoyUpdateDto deliveryBoyUpdateDto, Integer deliveryBoyId);

    PackageDelivery updatePackageDelivery(PackageDeliveryDto packageDeliveryDto, String userEmail);

}
