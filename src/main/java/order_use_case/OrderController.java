package order_use_case;

public class OrderController {

    final OrderInputBoundary orderInteractor;
    public OrderController(OrderInputBoundary orderInteractor) {
        this.orderInteractor = orderInteractor;
    }

    OrderResponseModel placeOrder(String buyerName, String buyerEmail, String residence, String[] foodItems) {
        OrderRequestModel requestModel = new OrderRequestModel(buyerName, buyerEmail, residence, foodItems);
        return orderInteractor.placeOrder(requestModel);
    }
}
