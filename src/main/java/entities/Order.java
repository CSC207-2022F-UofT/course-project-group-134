package entities;

import java.util.List;

public class Order {
    private final int orderNumber;
    private final Residence diningHall;
    private final Buyer buyer;
    private final List<FoodItem> foodItemList;
    private Seller seller;

    public Order(int orderNumber, Residence diningHall, Buyer buyer, List<FoodItem> foodItemList) {
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

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

}
