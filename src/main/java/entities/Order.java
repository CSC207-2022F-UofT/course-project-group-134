package entities;

public class Order {

    private DiningHall diningHall;
    private Buyer buyer;
    private FoodItem foodItem;
    private Seller seller;

    public Order(DiningHall diningHall, Buyer buyer, FoodItem foodItem) {
        this.diningHall = diningHall;
        this.buyer = buyer;
        this.foodItem = foodItem;
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

    public FoodItem getFoodItem() {
        return foodItem;
    }
}
