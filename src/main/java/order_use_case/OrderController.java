package order_use_case;

public class OrderController {

    final OrderInputBoundary orderInteractor;

    /**
     * This is the constructor.
     *
     * @param  orderInteractor
     */
    public OrderController(OrderInputBoundary orderInteractor) {
        this.orderInteractor = orderInteractor;
    }

    /**
     *
     *
     * @param buyerName
     * @param buyerEmail
     * @param residence     The dining hall the order is from
     * @param foodItems     An array of food items
     * @param foodQuantity  An array representing the quantity of food items
     * @param price
     * @return              A model representing any kind of response
     * @see OrderInputBoundary
     */
    public OrderResponseModel placeOrder(String buyerName, String buyerEmail, String residence, String[] foodItems, Integer[] foodQuantity, Double price) {
        OrderRequestModel requestModel = new OrderRequestModel(buyerName, buyerEmail, residence, foodItems, foodQuantity, price);
        return orderInteractor.placeOrder(requestModel);
    }
}
