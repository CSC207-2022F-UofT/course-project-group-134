package selling_use_case;

/**
 * SellingResponseModel contains the order number of the order
 * when the order is successful.
 */
public class SellingResponseModel {
    private final int orderNumber;
    public SellingResponseModel(int orderNumber){
        this.orderNumber = orderNumber;
    }
    public int getOrderNumber() {
        return this.orderNumber;
    }
}
