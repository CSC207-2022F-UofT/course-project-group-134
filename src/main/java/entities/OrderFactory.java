package entities;

import java.util.ArrayList;

public class OrderFactory {
        public Order create(DiningHall diningHall, Buyer buyer, ArrayList<FoodItem> foodItemList) {
        return new Order(diningHall, buyer, foodItemList);
    }
}
