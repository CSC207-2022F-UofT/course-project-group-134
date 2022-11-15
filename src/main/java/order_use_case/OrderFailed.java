package order_use_case;

public class OrderFailed extends RuntimeException{
    public OrderFailed(String error){
        super(error);
    }
}
