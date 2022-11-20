package order_use_case;

public class OrderDsModel {
    private final int orderID;

    private final String buyerName;
    private final String buyerEmail;
    private String sellerName;
    private String sellerEmail;

    private final String residence;
    private String status;
    private final String[] foodItems;
    private final Integer[] foodQuantity;
    private final Double price;

    public OrderDsModel(int orderID, String buyerName, String buyerEmail, String sellerName, String sellerEmail, String residence, String status, String[] foodItems, Integer[] foodQuantity, Double price) {
        this.orderID = orderID;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.residence = residence;
        this.status = status;
        this.foodItems = foodItems;
        this.foodQuantity = foodQuantity;
        this.price = price;
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

    public Integer[] getFoodQuantity() {
        return foodQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }
}
