package order_history_use_case;

import java.io.*;
import java.util.ArrayList;

public class OrderHistoryInteractor implements OrderHistoryInputBoundary {

    private final ArrayList<OrderHistoryResponseModel> allOrders;
    private final OrderHistoryOutputBoundary outputBoundary;
    public OrderHistoryInteractor(String username, String email, OrderHistoryOutputBoundary outputBoundary)
            throws IOException {

        OrderHistoryGateway gateway1 = new OrderHistoryGateway(username, email);
        this.allOrders = gateway1.getAllOrders();
        this.outputBoundary = outputBoundary;
    }


    @Override
    public ArrayList<String[]> returnViewListInteractor(OrderHistoryRequestModel reqMod){

        ArrayList<String[]> allOrdersList  = new ArrayList<>();

        for(OrderHistoryResponseModel resMod: this.allOrders){
            allOrdersList.add(this.outputBoundary.getViewList(resMod));
        }

        return allOrdersList;
    }

   /*
   // Scrapped function - use if needed
    public static void setPrice(OrderHistoryResponseModel responseModel){
        double price  = 0;
        String residence = responseModel.getResidence();
        for(String foodName: responseModel.getFoodItems()){

            try {
                price += OrderHistoryGateway.fetchPrice(foodName, residence);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        responseModel.setTotalPrice(price);
    }
     */

}
