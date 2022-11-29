package order_history_use_case;


import entities.FoodItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderHistoryPresenter implements OrderHistoryOutputBoundary{

    @Override
    public String[] getViewList(OrderHistoryResponseModel resMod) {
        List<String> foodItems = new ArrayList<String>();

        for(int i=0; i < resMod.getFoodItems().length; i++) {
            foodItems.add(resMod.getFoodItems()[i] + "(" + resMod.getFoodQuantity()[i].toString() + ")");
        }

        String[] viewList = {Integer.toString(resMod.getOrderID()), resMod.getBuyerName(),
        resMod.getBuyerEmail(), resMod.getSellerName(), resMod.getSellerEmail(), resMod.getResidence(),
                resMod.getOrderStatus(), String.join(", ", foodItems.toArray(new String[0])), Double.toString(resMod.getTotalPrice())};

        /*
        Order of stuff in the view list:
        0. orderID
        1. buyerName
        2. buyerEmail
        3. sellerName
        4 sellerEmail
        5. residence
        6. orderStatus
        7. foodItems + quantity
        8. totalPrice

        You can choose not to display buyerName, buyerEmail or sellerName, sellerEmail based on whether the user
        this method is called for is a BUYER or a SELLER.
         */

        return viewList;
    }

}
