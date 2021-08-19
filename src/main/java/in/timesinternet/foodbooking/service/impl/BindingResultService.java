package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.exception.InvalidRequestBodyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BindingResultService  {

    public void validate(BindingResult bindingResult){
        List<String> errorMessages = new ArrayList<>();

        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError -> errorMessages.add(objectError.getDefaultMessage()));
            throw new InvalidRequestBodyException(errorMessages.toString());
        }

    }

//    public void validate2(BindingResult bindingResult){
//        List<String> errorMessages = new ArrayList<>();
//        bindingResult.getAllErrors().forEach( objectError -> errorMessages.add(objectError.getDefaultMessage()) );
//        throw new RuntimeException(errorMessages.toString());
//    }


}
