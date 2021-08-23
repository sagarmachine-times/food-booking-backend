package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.PackageDelivery;
import in.timesinternet.foodbooking.entity.PackageDeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import java.lang.management.OperatingSystemMXBean;

public interface PackageDeliveryRepository extends JpaRepository<PackageDelivery, Integer> {

    Optional<PackageDelivery> findByPackageDeliveryDetail(PackageDeliveryDetail packageDeliveryDetail);
}
