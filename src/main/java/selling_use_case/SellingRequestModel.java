package selling_use_case;

/**
 * SellingRequestModel has the order information (sellingEmail and orderEmail) which the user inputs
 * into the selling screen.
 */
public class SellingRequestModel {
    private final String sellerEmail;
    private final int orderNumber;

    private final String sellerName;

    public SellingRequestModel(String sellerEmail, int orderNumber, String sellerName) {
        this.sellerEmail = sellerEmail;
        this.orderNumber = orderNumber;
        this.sellerName = sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getSellerName() {
        return sellerName;
    }
}
