package selling_use_case;

/**
 * SellingRequestModel has the order information (sellingEmail and orderEmail) which the user inputs
 * into the selling screen.
 */
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
