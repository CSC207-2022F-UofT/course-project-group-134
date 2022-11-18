package order_use_case;

import java.io.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderHistoryInteractor {

    //TODO: 1) get info from gateway (done),
    // 2) call the presenter with info from the gateway and return that

    private String userName;
    private String email;

    public OrderHistoryInteractor(String userName, String email){
        this.userName = userName;
        this.email = email;

    }

    public void fetchUserDetails(){

        // TODO: get user details from the correct csv file and pass it into the presenter

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
