package order_use_case;

import entities.FoodItem;

import java.util.ArrayList;

public class OrderDsResponseModel {
    private orderID;

    private String buyerName;
    private String buyerEmail;
    private String sellerName;
    private String sellerEmail;

    private String residence;
    private String status;
    private ArrayList<FoodItem> foodItems;

    public OrderDsResponseModel(int orderID, String buyerName, String buyerEmail, String sellerName, String sellerEmail, String residence, String foodItems) {
        this.orderID = orderID;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.residence = residence;
        this.status = status;
        this.foodItems = foodItems;
    }


}
