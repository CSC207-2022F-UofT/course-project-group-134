package order_use_case;

public class DoesNotExistException extends Exception{
    public DoesNotExistException(String error){
        super(error);
    }
}