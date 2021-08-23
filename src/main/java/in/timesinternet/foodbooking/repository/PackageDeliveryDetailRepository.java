package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.PackageDeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageDeliveryDetailRepository extends JpaRepository<PackageDeliveryDetail, Integer> {

    }
