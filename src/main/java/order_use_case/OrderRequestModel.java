package order_use_case;

public class OrderRequestModel {

    public final String buyerName;
    private final String buyerEmail;

    private final String residence;
    private final String[] foodItems;

    public OrderRequestModel(String buyerName, String buyerEmail, String residence, String[] foodItems) {
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.residence = residence;
        this.foodItems = foodItems;
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
}
