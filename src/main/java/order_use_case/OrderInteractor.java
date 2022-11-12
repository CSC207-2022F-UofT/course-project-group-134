package order_use_case;

import entities.Buyer;
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

    void public placeOrder(OrderRequestModel request) {
        //Model must have DiningHall and List of FoodItems

        //TODO: figure out how to retrieve Buyer instance
        Buyer buyer = new Buyer("random","password","email") //placeholder for workable code

        Order order = orderFactory.create(request.getDiningHall(), buyer, request.getFoodItems)
    }

    void public updateOrderStatus() {

    }

    void public getOrderStatus() {

    }

}