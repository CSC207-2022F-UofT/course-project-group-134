package order_history_use_case;

import java.util.ArrayList;

public class OrderHistoryResponseModel {

    //  Each order should be a ResponseModel, which means that the interactor will return an arraylist of ResponseModels

    private Integer orderID;
    private String sellerName;
    private String sellerEmail;
    private String residence;
    private String orderStatus;
    private ArrayList<String> foodItems;
    private Double totalPrice;

    public OrderHistoryResponseModel(Integer orderID, String sellerName, String sellerEmail, String residence,
                                     String orderStatus, ArrayList<String> foodItems){

        this.orderID = orderID;
        this.sellerEmail = sellerEmail;
        this.sellerName = sellerName;
        this.residence = residence;
        this.orderStatus = orderStatus;
        this.foodItems = foodItems;
        this.totalPrice = 0.0;
    }

    // This method will be called by the presenter after calculating the price
    public void setTotalPrice(double price){
        this.totalPrice = price;
    }

    public ArrayList<String> getFoodItems() {
        return foodItems;
    }

    public String getResidence() {
        return residence;
    }
}
