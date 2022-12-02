package selling_use_case;

/**
 * SellingFailed exception is an exception that gets raised when
 * the user is unsuccessful at selling.
 */
public class SellingFailed extends RuntimeException{
    public SellingFailed(String error){
        super(error);
    }
}