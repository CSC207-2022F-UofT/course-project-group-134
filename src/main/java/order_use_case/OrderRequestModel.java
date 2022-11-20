package order_use_case;

public class OrderRequestModel {

    public final String buyerName;
    private final String buyerEmail;
    private final String residence;
    private final String[] foodItems;
    private final Integer[] foodQuantity;
    private final Double price;

    public OrderRequestModel(String buyerName, String buyerEmail, String residence, String[] foodItems, Integer[] foodQuantity, Double price) {
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.residence = residence;
        this.foodItems = foodItems;
        this.foodQuantity = foodQuantity;
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

    public Integer[] getFoodQuantity() {
        return foodQuantity;
    }

    public Double getPrice() {
        return price;
    }
}
