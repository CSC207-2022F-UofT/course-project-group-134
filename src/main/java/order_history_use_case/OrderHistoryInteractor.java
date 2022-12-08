package order_history_use_case;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryInteractor implements OrderHistoryInputBoundary {

    private final List<OrderHistoryResponseModel> allOrders;
    private final OrderHistoryOutputBoundary outputBoundary;

    /**
     *
     * @param username  The username of the user whose details we wish to display
     * @param email The email of the user whose details we wish to display
     * @param outputBoundary The UseCase output boundary we use to process the data into a form that can be displayed
     *                       on the view
     * @throws IOException if the file containing the orders list does not exist
     */
    public OrderHistoryInteractor(String username, String email, String csvPath, OrderHistoryOutputBoundary outputBoundary)
            throws IOException {
        OrderHistoryGateway gateway1 = new OrderHistoryGateway(username, email, csvPath);
        this.allOrders = gateway1.getAllOrders();
        this.outputBoundary = outputBoundary;
    }

    /**
     *
     * @return Returns all the finished orders that the OutputBoundary returns
     */
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

    /**
     *
     * @return Returns all the unfinished orders that the OutputBoundary returns
     */
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
