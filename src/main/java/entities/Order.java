package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

    private DiningHall diningHall;
    private Buyer buyer;
    private ArrayList<FoodItem> foodItemList;
    private Seller seller;
    private LocalDateTime creationTime;
    private LocalDateTime acceptTime;
    private LocalDateTime completionTime;

    public Order(DiningHall diningHall, Buyer buyer, ArrayList<FoodItem> foodItemList, LocalDateTime creationTime) {
        this.diningHall = diningHall;
        this.buyer = buyer;
        this.foodItemList = foodItemList;
        this.creationTime = creationTime;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setAcceptTime(LocalDateTime acceptTime) {this.creationTime = creationTime;}

    public void setAcceptTime(LocalDateTime acceptTime) {this.creationTime = creationTime;}

    public void setCompletionTime(LocalDateTime completionTime) {this.completionTime = completionTime;}

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
