package selling_use_case;

import entities.Order;

public class SellingResponseModel {
    private int orderNumber;
    public SellingResponseModel(int orderNumber){
        this.orderNumber = orderNumber;
    }
    public int getOrderNumber() {
        return this.orderNumber;
    }
}
