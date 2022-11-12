package order_use_case;

import entities.Order;

public interface OrderInputBoundary {
    OrderResponseModel placeOrder(OrderRequestModel request);

    OrderResponseModel acceptOrder(OrderRequestModel request);
}
