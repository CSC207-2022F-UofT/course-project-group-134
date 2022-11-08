package entities;

public class Order {

    private DiningHall diningHall;
    private Buyer buyer;
    private FoodItem foodItem;

    public Order(DiningHall diningHall, Buyer buyer, FoodItem foodItem) {
        this.diningHall = diningHall;
        this.buyer = buyer;
        this.foodItem = foodItem;
    }

    public DiningHall getDiningHall() {
        return diningHall;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }
}
