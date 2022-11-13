package selling_use_case;

public class SellingFailed extends RuntimeException{
    public SellingFailed(String error){
        super(error);
    }
}