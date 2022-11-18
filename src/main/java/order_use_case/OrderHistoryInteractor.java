package order_use_case;

import entities.Order;
import entities.User;

import java.io.*;
import java.util.ArrayList;;

public class OrderHistoryInteractor {

    // TODO: call the presenter

    private User user;

    public OrderHistoryInteractor(User user) throws IOException {
        this.user = user;
        OrderHistoryGateway gateway1 = new OrderHistoryGateway(user);
        ArrayList<OrderHistoryRequestModel> allOrders = gateway1.getAllOrders();

        // TODO: call presenter
    }

    public static void setPrice(OrderHistoryResponseModel responseModel){
        double price  = 0;
        String residence = responseModel.getResidence();
        for(String foodName: responseModel.getFoodItems()){

            try {
                price += OrderHistoryGateway.fetchPrice(foodName, residence);
            }
            catch (IOException e){
                e.printStackTrace();
                // TODO: decide whether to keep this or do continue; instead
            }
        }

        responseModel.setTotalPrice(price);
    }

}
