package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {
}
