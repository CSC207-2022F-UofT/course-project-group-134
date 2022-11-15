package order_use_case;

public class OrderRequestModel {

    public final String buyerName;
    private final String buyerEmail;
    private final String sellerName;
    private final String sellerEmail;

    private final String residence;
    private final String[] foodItems;

    public OrderRequestModel(String buyerName, String buyerEmail, String sellerName, String sellerEmail, String residence, String[] foodItems) {
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.residence = residence;
        this.foodItems = foodItems;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getResidence() {
        return residence;
    }

    public String[] getFoodItems() {
        return foodItems;
    }
}