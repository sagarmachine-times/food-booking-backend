package in.timesinternet.foodbooking.contoller;

import in.timesinternet.foodbooking.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value ={InvalidRequestBodyException.class, UserAlreadyExistException.class,
            NotFoundException.class, UnauthorizedException.class, AlreadyExistException.class,
    InvalidRequestException.class})
    public ResponseEntity<HashMap<String,String>> runTimeExceptionHandler(RuntimeException runtimeException){

        return ResponseEntity.badRequest().body(new HashMap<String, String>(){{put("message",runtimeException.getMessage());}});
    }


//    @ExceptionHandler(value = UserAlreadyExistException.class)
//    public ResponseEntity<HashMap<String,String>> userAlreadyExistExceptionHandler(UserAlreadyExistException userAlreadyExistException){
//        return ResponseEntity.badRequest().body(new HashMap<String, String>(){{put("message",userAlreadyExistException.getMessage());}});
//    }



//    @ExceptionHandler(value = RuntimeException.class)
//    public ResponseEntity<HashMap<String,String>> handler2(RuntimeException runtimeException)
//    {
//        return ResponseEntity.badRequest().body(new HashMap<String, String>(){{put("message","runtime exception happened"+runtimeException.getMessage());}});
//    }

}
