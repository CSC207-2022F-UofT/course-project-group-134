package order_use_case;

import entities.Order;
import entities.OrderFactory;

public class OrderInteractor implements OrderInputBoundary {
    final OrderDsGateway orderDsGateway;
    final OrderPresenter orderPresenter;
    final OrderFactory orderFactory;

    public OrderInteractor(OrderDsGateway orderDsGateway, OrderPresenter orderPresenter, OrderFactory orderFactory) {
        this.orderDsGateway = orderDsGateway;
        this.orderPresenter = orderPresenter;
        this.orderFactory = orderFactory;
    }

    
}