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
        // TODO: How can the order fail?

        String sellerName = "null";
        String sellerEmail = "null";
        String status = OrderStatusType.ORDERED.toString();
        System.out.println("ORDER INTERACTOR: SENT ORDER MADE BY " + request.getBuyerName());

        OrderDsRequestModel orderDsRequest = new OrderDsRequestModel(request.getBuyerName(), request.getBuyerEmail(),
                sellerName, sellerEmail, request.getResidence(), status, request.getFoodItems(), request.getFoodQuantity(), request.getPrice());
        orderDsGateway.saveOrder(orderDsRequest);

        OrderResponseModel responseModel = new OrderResponseModel();
        return orderPresenter.prepareSuccessView(responseModel);
    }
}