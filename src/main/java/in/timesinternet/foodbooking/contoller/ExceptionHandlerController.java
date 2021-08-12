package in.timesinternet.foodbooking.contoller;

import in.timesinternet.foodbooking.exception.InvalidRequestBodyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = InvalidRequestBodyException.class)
    public ResponseEntity<HashMap<String,String>> handler(InvalidRequestBodyException invalidRequestBodyException){

        return ResponseEntity.badRequest().body(new HashMap<String, String>(){{put("message",invalidRequestBodyException.getMessage());}});
    }

}
