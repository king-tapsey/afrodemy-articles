package zw.co.afrocodemy.afrocodemyarticles.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message){
        super(message);
    }
}
