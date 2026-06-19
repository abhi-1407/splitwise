package exceptions;

public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException(){
        super("User already exists");
    }
}
