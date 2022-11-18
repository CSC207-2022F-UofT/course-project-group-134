package selling_use_case;

import entities.Order;
import entities.Seller;

public class SellingRequestModel {
    private String sellerEmail;
    private int orderNumber;

    public SellingRequestModel(String sellerEmail, int orderNumber) {
        this.sellerEmail = sellerEmail;
        this.orderNumber = orderNumber;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
