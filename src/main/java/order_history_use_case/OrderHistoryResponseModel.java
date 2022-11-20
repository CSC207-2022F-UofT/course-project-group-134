package order_history_use_case;

public class OrderHistoryResponseModel {

    //  Each order should be a ResponseModel, which means that the interactor will return an arraylist of ResponseModels

    private final Integer orderID;
    private final String buyerName;
    private final String buyerEmail;
    private final String sellerName;
    private final String sellerEmail;
    private final String residence;
    private final String orderStatus;
    private final String[] foodItems;
    private final Double totalPrice;

    public OrderHistoryResponseModel(Integer orderID, String buyerName, String buyerEmail,
                                     String sellerName, String sellerEmail, String residence,
                                     String orderStatus, String[] foodItems, double price){

        this.orderID = orderID;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.sellerEmail = sellerEmail;
        this.sellerName = sellerName;
        this.residence = residence;
        this.orderStatus = orderStatus;
        this.foodItems = foodItems;
        this.totalPrice = price;
    }
    public Integer getOrderID() {
        return orderID;
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
    public String getOrderStatus() {
        return orderStatus;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public String[] getFoodItems() {
        return foodItems;
    }
}
