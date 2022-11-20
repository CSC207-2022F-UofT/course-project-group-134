package order_history_use_case;


import entities.FoodItem;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderHistoryPresenter implements OrderHistoryOutputBoundary{

    @Override
    public String[] getViewList(OrderHistoryResponseModel resMod) {

        String foodItems = String.join(", ", resMod.getFoodItems());

        String[] viewList = {Integer.toString(resMod.getOrderID()), resMod.getBuyerName(),
        resMod.getBuyerEmail(), resMod.getSellerName(), resMod.getSellerEmail(), resMod.getResidence(),
                resMod.getOrderStatus(), foodItems, Double.toString(resMod.getTotalPrice())};

        /*
        Order of stuff in the view list:
        1. orderID
        2. buyerName
        3. buyerEmail
        4. sellerName
        5. sellerEmail
        6. residence
        7. orderStatus
        8. foodItems
        9. totalPrice

        You can choose not to display buyerName, buyerEmail or sellerName, sellerEmail based on whether the user
        this method is called for is a BUYER or a SELLER.
         */

        return viewList;
    }

}
