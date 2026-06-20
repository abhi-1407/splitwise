package exceptions;

public class InvalidDebtException extends RuntimeException{
    public InvalidDebtException(){
        super("Amount is more than the debt");
    }
}
