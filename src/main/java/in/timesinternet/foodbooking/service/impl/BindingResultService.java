package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.exception.InvalidRequestBodyException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class BindingResultService  {

    public void validate(BindingResult bindingResult){

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        System.out.println(bindingResult.hasErrors());
        for (ObjectError objectError:allErrors){
            throw  new InvalidRequestBodyException(objectError.getDefaultMessage());
        }
    }
}
