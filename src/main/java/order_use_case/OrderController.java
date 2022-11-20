package order_use_case;

import entities.ResidenceType;

public class OrderController {

    final OrderInputBoundary orderInteractor;
    public OrderController(OrderInputBoundary orderInteractor) {
        this.orderInteractor = orderInteractor;
    }

    OrderResponseModel placeOrder(String buyerName, String buyerEmail, String residence, String[] foodItems, Double price) {
        OrderRequestModel requestModel = new OrderRequestModel(buyerName, buyerEmail, residence, foodItems, price);
        return orderInteractor.placeOrder(requestModel);
    }
}
