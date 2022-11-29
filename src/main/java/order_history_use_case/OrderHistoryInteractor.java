package order_history_use_case;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryInteractor implements OrderHistoryInputBoundary {

    private final List<OrderHistoryResponseModel> allOrders;
    private final OrderHistoryOutputBoundary outputBoundary;
    public OrderHistoryInteractor(String username, String email, OrderHistoryOutputBoundary outputBoundary)
            throws IOException {
        System.out.println("interactor says email is " + email);
        OrderHistoryGateway gateway1 = new OrderHistoryGateway(username, email);
        this.allOrders = gateway1.getAllOrders();
        this.outputBoundary = outputBoundary;
    }


    @Override
    public List<String[]> returnFinishedOrders(){
        List<String[]> allOrdersList  = new ArrayList<>();

        for(OrderHistoryResponseModel resMod: this.allOrders){
            if (resMod.getOrderStatus().equals("FINISHED")) {
                allOrdersList.add(this.outputBoundary.getViewList(resMod));
            }
        }

        return allOrdersList;
    }

    @Override
    public List<String[]> returnCurrentOrders(){
        List<String[]> allOrdersList  = new ArrayList<>();

        for(OrderHistoryResponseModel resMod: this.allOrders) {
            if (!resMod.getOrderStatus().equals("FINISHED")) {
                allOrdersList.add(this.outputBoundary.getViewList(resMod));
            }
        }

        return allOrdersList;
    }

}
