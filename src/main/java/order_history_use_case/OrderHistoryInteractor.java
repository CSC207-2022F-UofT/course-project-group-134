package order_history_use_case;

import java.io.*;
import java.util.ArrayList;

public class OrderHistoryInteractor implements OrderHistoryInputBoundary {

    private final ArrayList<OrderHistoryResponseModel> allOrders;
    private final OrderHistoryOutputBoundary outputBoundary;
    public OrderHistoryInteractor(String username, String email, OrderHistoryOutputBoundary outputBoundary)
            throws IOException {
        System.out.println("interactor says email is " + email);
        OrderHistoryGateway gateway1 = new OrderHistoryGateway(username, email);
        this.allOrders = gateway1.getAllOrders();
        this.outputBoundary = outputBoundary;
    }


    @Override
    public ArrayList<String[]> returnFinishedOrders(){

        ArrayList<String[]> allOrdersList  = new ArrayList<>();

        for(OrderHistoryResponseModel resMod: this.allOrders){
            if (resMod.getOrderStatus().equals("FINISHED")) {
                allOrdersList.add(this.outputBoundary.getViewList(resMod));
            }
        }

        return allOrdersList;
    }

    @Override
    public ArrayList<String[]> returnCurrentOrders(){
        ArrayList<String[]> allOrdersList  = new ArrayList<>();

        for(OrderHistoryResponseModel resMod: this.allOrders) {
            if (!resMod.getOrderStatus().equals("FINISHED")) {
                allOrdersList.add(this.outputBoundary.getViewList(resMod));
            }
        }

        return allOrdersList;
    }

}
