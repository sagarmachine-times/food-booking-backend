package in.timesinternet.foodbooking.exception;

public class InvalidRequestException extends  RuntimeException{
    public InvalidRequestException(String message){
        super(message);
    }
}