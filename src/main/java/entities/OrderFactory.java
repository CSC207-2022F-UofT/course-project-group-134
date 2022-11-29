package entities;

import java.util.ArrayList;

public class OrderFactory {

    public Order create(int orderNumber, Residence diningHall, Buyer buyer, ArrayList<FoodItem> foodItemList) {
        return new Order(orderNumber, diningHall, buyer, foodItemList);
    }

}
