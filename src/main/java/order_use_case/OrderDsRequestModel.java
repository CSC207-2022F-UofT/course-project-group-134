package order_use_case;

public class OrderDsRequestModel {

    public final String buyerName;
    private final String buyerEmail;
    private final String sellerName;
    private final String sellerEmail;

    private final String residence;
    private final String status;
    private final String[] foodItems;
    private final Integer[] foodQuantity;
    private final Double price;

    /**
     *
     * @param buyerName     Buyer name
     * @param buyerEmail    Buyer email
     * @param sellerName    Seller name
     * @param sellerEmail   Seller email
     * @param residence     Dining hall
     * @param status        Status from OrderStatusType
     * @param foodItems     The list of food items
     * @param foodQuantity  The list of food quantities bought
     * @param price         Price
     */
    public OrderDsRequestModel(String buyerName, String buyerEmail, String sellerName, String sellerEmail, String residence, String status, String[] foodItems, Integer[] foodQuantity, Double price) {
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.residence = residence;
        this.status = status;
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

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getResidence() {
        return residence;
    }

    public String getStatus() {
        return status;
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
