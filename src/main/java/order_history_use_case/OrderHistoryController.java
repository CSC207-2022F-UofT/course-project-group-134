package order_history_use_case;


import java.util.ArrayList;

public class OrderHistoryController{


    private OrderHistoryRequestModel reqMod;
    private OrderHistoryInputBoundary inputBoundary;

    public OrderHistoryController(String username, String email, OrderHistoryInputBoundary interactor){
        this.reqMod = new OrderHistoryRequestModel(username, email);
        this.inputBoundary = interactor;
    }

    public ArrayList<String[]> returnFinishedOrders(){
        // Returns everything that is to be displayed as a list of string arrays
        return inputBoundary.returnFinishedOrders();
    }

    public ArrayList<String[]> returnCurrentOrders(){
        return inputBoundary.returnCurrentOrders();
    }

}
