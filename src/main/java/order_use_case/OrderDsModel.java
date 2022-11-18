package order_use_case;

public class OrderDsModel {
    private final int orderID;

    public final String buyerName;
    private final String buyerEmail;
    private final String sellerName;
    private final String sellerEmail;

    private final String residence;
    private final String status;
    private final String[] foodItems;

    public OrderDsModel(int orderID, String buyerName, String buyerEmail, String sellerName, String sellerEmail, String residence, String status, String[] foodItems) {
        this.orderID = orderID;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.residence = residence;
        this.status = status;
        this.foodItems = foodItems;
    }

    public int getOrderID() {
        return this.orderID;
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

    public String getStatus() {
        return status;
    }

    public String[] getFoodItems() {
        return foodItems;
    }
}
