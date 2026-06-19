package exceptions;

public class InvalidGroupException extends RuntimeException{
    public InvalidGroupException(String message){
        super(message);
    }
}
