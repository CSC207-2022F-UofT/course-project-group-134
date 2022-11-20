package selling_use_case;

public class SellingRequestModel {
    private final String sellerEmail;
    private final int orderNumber;

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
