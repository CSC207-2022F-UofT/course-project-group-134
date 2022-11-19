package order_history_use_case;

public class OrderHistoryRequestModel {

    private Integer orderID;
    private String buyerUsername;
    private String  buyerEmail;
    private String sellerName;
    private String sellerEmail;
    private String residence;
    private String orderStatus;
    private String[] foodItems;

    public OrderHistoryRequestModel(Integer orderID, String  buyerUsername, String buyerEmail,
                                    String sellerName, String sellerEmail,
                                    String  residence, String orderStatus, String[] foodItems){
        this.orderID =  orderID;
        this.buyerUsername = buyerUsername;
        this.buyerEmail = buyerEmail;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.residence = residence;
        this.orderStatus = orderStatus;
        this.foodItems = foodItems;
    }

}
