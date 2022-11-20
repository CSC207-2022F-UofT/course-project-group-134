package order_use_case;

public class OrderController {

    final OrderInputBoundary orderInteractor;
    public OrderController(OrderInputBoundary orderInteractor) {
        this.orderInteractor = orderInteractor;
    }

    void placeOrder(String buyerName, String buyerEmail, String residence, String[] foodItems, Double price) {
        OrderRequestModel requestModel = new OrderRequestModel(buyerName, buyerEmail, residence, foodItems, price);
        orderInteractor.placeOrder(requestModel);
    }
}
