package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.PackageStatusDto;
import in.timesinternet.foodbooking.entity.Package;

import java.util.List;

public interface PackageService {

    Package updatePackageStatus(PackageStatusDto packageStatusDto);

    Package getPackage(Integer packageId);
}
