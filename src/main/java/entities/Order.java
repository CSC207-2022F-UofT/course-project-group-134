package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

    private DiningHall diningHall;
    private Buyer buyer;
    private ArrayList<FoodItem> foodItemList;
    private Seller seller;

    public Order(DiningHall diningHall, Buyer buyer, ArrayList<FoodItem> foodItemList) {
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

    public DiningHall getDiningHall() {
        return diningHall;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public ArrayList<FoodItem> getFoodItemList() {
        return foodItemList;
    }

}
