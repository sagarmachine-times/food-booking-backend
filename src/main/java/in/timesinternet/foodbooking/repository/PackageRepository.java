package in.timesinternet.foodbooking.repository;

import in.timesinternet.foodbooking.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {

}
