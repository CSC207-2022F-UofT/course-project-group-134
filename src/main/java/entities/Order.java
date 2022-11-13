package entities;

import java.util.ArrayList;

public class Order {

    private Residence diningHall;
    private Buyer buyer;
    private ArrayList<FoodItem> foodItemList;
    private Seller seller;

    public Order(Residence diningHall, Buyer buyer, ArrayList<FoodItem> foodItemList) {
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
