package order_use_case;

public interface OrderInputBoundary {
    OrderResponseModel placeOrder(OrderRequestModel request);
}
