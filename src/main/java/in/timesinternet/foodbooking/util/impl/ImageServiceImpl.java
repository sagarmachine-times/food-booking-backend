package in.timesinternet.foodbooking.util.impl;

import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.util.ImageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;

@Component
public class ImageServiceImpl implements ImageService {
    String url = "https://api.imgbb.com/1/upload?key=dcbdc94a138d3a04d52f008ec67168a5";


    @Override
    public Image uploadImage(MultipartFile file) {
        Image image = new Image();
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        try {
            map.add("image", Base64.getEncoder().encodeToString(file.getBytes()));

        } catch (Exception ex) {
            throw new NotFoundException("image was not saved try a different image");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, httpHeaders);


        HashMap<String, Object> result = restTemplate.exchange(url, HttpMethod.POST, request, HashMap.class).getBody();
        //  System.out.println((String)((HashMap)result.get("data")).get("display_url"));
        image.setMainUrl((String) ((HashMap) result.get("data")).get("display_url"));
        image.setThumbnailUrl((String) ((HashMap)((HashMap) result.get("data")).get("thumb")).get("url"));
        image.setDeleteUrl(((String)((HashMap) result.get("data")).get("delete_url")));
        System.out.println(image);
        return image;
    }

    @Override
    public void deleteImage(String deleteUrl) {

    }
}
