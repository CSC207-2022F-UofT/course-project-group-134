package selling_use_case;

import entities.Order;

public class SellingResponseModel {
    private Order order;
    public SellingResponseModel(Order order){
        this.order = order;
    }
    public Order getOrder(){
        return this.order;
    }
}
