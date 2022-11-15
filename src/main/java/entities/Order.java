package entities;

import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private Residence diningHall;
    private Buyer buyer;
    private ArrayList<FoodItem> foodItemList;
    private Seller seller;

    public Order(int orderNumber, Residence diningHall, Buyer buyer, ArrayList<FoodItem> foodItemList) {
        this.orderNumber = orderNumber;
        this.diningHall = diningHall;
        this.buyer = buyer;
        this.foodItemList = foodItemList;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    public Residence getDiningHall() {
        return diningHall;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public ArrayList<FoodItem> getFoodItemList() {
        return foodItemList;
    }
}
