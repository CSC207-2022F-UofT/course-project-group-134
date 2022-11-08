package entities;

public class Order {

    DiningHall diningHall;
    Buyer buyer;
    FoodItem foodItem;

    public Order(DiningHall diningHall, Buyer buyer, FoodItem foodItem) {
        this.diningHall = diningHall;
        this.buyer = buyer;
        this.foodItem = foodItem;
    }
}
