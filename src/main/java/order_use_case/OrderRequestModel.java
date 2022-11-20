package order_use_case;

public class OrderRequestModel {

    public final String buyerName;
    private final String buyerEmail;
    private final String residence;
    private final String[] foodItems;
    private final Double price;

    public OrderRequestModel(String buyerName, String buyerEmail, String residence, String[] foodItems, Double price) {
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.residence = residence;
        this.foodItems = foodItems;
        this.price = price;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getResidence() {
        return residence;
    }

    public String[] getFoodItems() {
        return foodItems;
    }

    public Double getPrice() {
        return price;
    }
}
