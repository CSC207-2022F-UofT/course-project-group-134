package order_use_case;

import java.util.ArrayList;

import entities.Order;
import entities.Buyer;
import entities.DiningHall;

public interface OrderDsGateway {
    //TODO: functions we need
    // getOrders(DiningHall) for Sellers
    // saveOrder(RequestModel)
    // We will have one order per person
    // getOrder(Buyer) for buyers that want to update
    // updateOrder(OrderDsModel) // might be possible to just make new order and update order one method

    ArrayList<OrderDsResponseModel> getOrders(DiningHall diningHall);

    boolean saveOrder(Order);

    OrderDsResponseModel getOrder(Buyer buyer); //use email

    boolean updateOrder(Order);

}
