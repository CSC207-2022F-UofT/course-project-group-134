package selling_use_case;

import entities.Order;
import entities.Seller;

public class SellingRequestModel {
    private Seller seller;
    private Order order;

    public SellingRequestModel(Seller seller, Order order) {
        this.seller = seller;
        this.order = order;
    }

    public Seller getSeller() {
        return seller;
    }

    public Order getOrder() {
        return order;
    }
}
