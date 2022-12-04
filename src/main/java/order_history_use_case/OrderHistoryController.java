package order_history_use_case;


import java.util.ArrayList;
import java.util.List;

public class OrderHistoryController{


    private OrderHistoryRequestModel reqMod;
    private OrderHistoryInputBoundary inputBoundary;

    /**
     *
     * @param username The username of the user whose order history is to be displayed
     * @param email The email of the user whose order history is to be displayed
     * @param interactor The Use Case interactor for the OrderHistory use case
     */
    public OrderHistoryController(String username, String email, OrderHistoryInputBoundary interactor){
        this.reqMod = new OrderHistoryRequestModel(username, email);
        this.inputBoundary = interactor;
    }

    /**
     *
     * @return Returns all the orders whose status is "FINISHED" as a list of string[], where each string[]
     * contains information about a particular order.
     */
    public List<String[]> returnFinishedOrders(){
        return inputBoundary.returnFinishedOrders();
    }

    /**
     *
     * @return Returns all the orders whose status is anything except FINISHED" as a list of string[],
     * where each string[] contains information about a particular order.
     */
    public List<String[]> returnCurrentOrders(){
        return inputBoundary.returnCurrentOrders();
    }

}
