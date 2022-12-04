package order_use_case;

import entities.OrderStatusType;


public class OrderInteractor implements OrderInputBoundary {
    final OrderDsGateway orderDsGateway;
    final OrderOutputBoundary orderPresenter;

    /**
     * The constructor of this class.
     *
     * @param orderDsGateway The gateway to access the orders.
     * @param orderPresenter The presenter that processes the output.
     */
    public OrderInteractor(OrderDsGateway orderDsGateway, OrderOutputBoundary orderPresenter) {
        this.orderDsGateway = orderDsGateway;
        this.orderPresenter = orderPresenter;
    }

    /**
     *
     * @param request The model containing the order information.
     * @return a model of the response
     */
    @Override
     public OrderResponseModel placeOrder(OrderRequestModel request) {
        // TODO: How can the order fail?

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