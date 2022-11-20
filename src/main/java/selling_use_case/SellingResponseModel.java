package selling_use_case;

public class SellingResponseModel {
    private final int orderNumber;
    public SellingResponseModel(int orderNumber){
        this.orderNumber = orderNumber;
    }
    public int getOrderNumber() {
        return this.orderNumber;
    }
}
