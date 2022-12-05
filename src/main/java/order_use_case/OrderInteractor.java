package order_use_case;

import entities.OrderFactory;
import entities.OrderStatusType;


public class OrderInteractor implements OrderInputBoundary {
    final OrderDsGateway orderDsGateway;
    final OrderOutputBoundary orderPresenter;
    final OrderFactory orderFactory;

    public OrderInteractor(OrderDsGateway orderDsGateway, OrderOutputBoundary orderPresenter, OrderFactory orderFactory) {
        this.orderDsGateway = orderDsGateway;
        this.orderPresenter = orderPresenter;
        this.orderFactory = orderFactory;
    }

    @Override
     public OrderResponseModel placeOrder(OrderRequestModel request) {
        if (request.getFoodItems().length == 0) {
            throw new OrderFailed("No food items were ordered.");
        }

        String sellerName = "null";
        String sellerEmail = "null";
        String status = OrderStatusType.ORDERED.toString();

        OrderDsRequestModel orderDsRequest = new OrderDsRequestModel(request.getBuyerName(), request.getBuyerEmail(),
                sellerName, sellerEmail, request.getResidence(), status, request.getFoodItems(), request.getFoodQuantity(), request.getPrice());
        orderDsGateway.saveOrder(orderDsRequest);

        System.out.println("ORDER INTERACTOR: SENT ORDER MADE BY " + request.getBuyerName());

        OrderResponseModel responseModel = new OrderResponseModel();
        return orderPresenter.prepareSuccessView(responseModel);
    }
}