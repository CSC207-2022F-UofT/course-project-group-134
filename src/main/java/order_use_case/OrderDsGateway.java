package order_use_case;


import entities.OrderStatusType;

import java.util.ArrayList;

public interface OrderDsGateway {
    void saveOrder(OrderDsRequestModel orderModel);

    ArrayList<Integer> getUnfulfilledOrders(String sellerResidence);

    ArrayList<Integer> getFinishedOrders(String sellerEmail);

    OrderDsModel getOrderInfo(int orderNumber);

    boolean orderExistsById(int orderNumber);
    OrderStatusType getOrderStatus(int orderNumber);

    void setOrderStatus(int orderNumber, OrderStatusType status);

    void updateOrder(int orderNumber, OrderStatusType status, String sellerEmail);

    boolean sellerHasOrder(String sellerEmail);

    int getOrderNumberFromSellerEmail(String sellerEmail) throws DoesNotExistException;

    public double getPriceFromOrderNumber(int orderNumber) throws DoesNotExistException;
}
