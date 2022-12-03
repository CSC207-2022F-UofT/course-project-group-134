package order_use_case;


import entities.OrderStatusType;

import java.util.ArrayList;
import java.util.List;

public interface OrderDsGateway {
    void saveOrder(OrderDsRequestModel orderModel);

    List<Integer> getUnfulfilledOrders(String sellerResidence);

    List<Integer> getFinishedOrders(String sellerEmail);

    OrderDsModel getOrderInfo(int orderNumber);

    boolean orderExistsById(int orderNumber);
    OrderStatusType getOrderStatus(int orderNumber);

    void setOrderStatus(int orderNumber, OrderStatusType status);

    void updateOrder(int orderNumber, OrderStatusType status, String sellerEmail, String sellerName);

    boolean sellerHasOrder(String sellerEmail);

    ArrayList<Integer> getOrderNumbersFromSellerEmail(String sellerEmail);

    double getPriceFromOrderNumber(int orderNumber) throws DoesNotExistException;
}
