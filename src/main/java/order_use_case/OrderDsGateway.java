package order_use_case;


import entities.OrderStatusType;

import java.util.ArrayList;

public interface OrderDsGateway {
    void saveOrder(OrderDsRequestModel orderModel);

    ArrayList<Integer> getUnfulfilledOrders();

    OrderDsModel getOrderInfo(int orderNumber);

    boolean orderExistsById(int orderNumber);
    OrderStatusType getOrderStatus(int orderNumber);

    void setOrderStatus(int orderNumber, OrderStatusType status);

    void updateOrder(int orderNumber, OrderStatusType status, String sellerUsername);

    boolean sellerHasOrder(String sellerEmail);

    int getOrderNumberFromSellerEmail(String sellerEmail) throws DoesNotExistException;
}
