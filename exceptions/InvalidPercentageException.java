package exceptions;

public class InvalidPercentageException extends RuntimeException{
    public InvalidPercentageException(){
        super("Percentages must add to 100");
    }
}
