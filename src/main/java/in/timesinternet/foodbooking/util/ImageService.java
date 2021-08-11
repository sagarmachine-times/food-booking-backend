package in.timesinternet.foodbooking.util;

import in.timesinternet.foodbooking.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image uploadImage(MultipartFile file);
    void deleteImage(String deleteUrl);
}
