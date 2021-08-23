package in.timesinternet.foodbooking.service.impl;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import in.timesinternet.foodbooking.entity.Package;
import in.timesinternet.foodbooking.entity.Staff;
import in.timesinternet.foodbooking.dto.request.PackageStatusDto;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.exception.UnauthorizedException;
import in.timesinternet.foodbooking.repository.PackageRepository;
import in.timesinternet.foodbooking.service.OrderService;
import in.timesinternet.foodbooking.service.PackageService;
import in.timesinternet.foodbooking.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {


    @Autowired
    PackageRepository packageRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    OrderService orderService;

    @Override
    public Package updatePackageStatus(PackageStatusDto packageStatusDto) {

        Package pack = getPackage(packageStatusDto.getPackageId());

        pack.setStatus(packageStatusDto.getPackageStatus());
        return packageRepository.save(pack);

    }

    @Override
    public Package getPackage(Integer packageId)
    {
        Optional<Package> packageOptional = packageRepository.findById(packageId);

        if (packageOptional.isPresent())
        {
            return packageOptional.get();
        }
        else
        {
            throw new NotFoundException("package with given id is not found");
        }
    }

}
