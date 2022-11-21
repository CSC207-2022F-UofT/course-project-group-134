package order_use_case;

public class OrderController {

    final OrderInputBoundary orderInteractor;
    public OrderController(OrderInputBoundary orderInteractor) {
        this.orderInteractor = orderInteractor;
    }

    public OrderResponseModel placeOrder(String buyerName, String buyerEmail, String residence, String[] foodItems, Integer[] foodQuantity, Double price) {
        OrderRequestModel requestModel = new OrderRequestModel(buyerName, buyerEmail, residence, foodItems, foodQuantity, price);
        return orderInteractor.placeOrder(requestModel);
    }
}
