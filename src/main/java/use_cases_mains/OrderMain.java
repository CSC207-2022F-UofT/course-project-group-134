package use_cases_mains;

import entities.OrderFactory;
import entities.ResidenceType;
import order_use_case.*;

import java.io.IOException;

public class OrderMain {

    public static void main(String[] args) {
        OrderDsGateway orders;
        try {
            orders = new OrderGateway("./src/main/java/data_storage/orders.csv");
            System.out.println("File Created!");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        OrderOutputBoundary orderPresenter = new OrderPresenter();
        OrderInputBoundary orderInteractor = new OrderInteractor(orders, orderPresenter);
        OrderController orderController = new OrderController(orderInteractor);
        String[] foodItems = {"Burger", "Fries"};
        Integer[] foodQuantity = {1,1};
        String[] foodItems2 = {"Burger", "Burger", "Poutine", "Burger"};
        Integer[] foodQuantity3 = {1,1,1,1};
        Integer[] foodQuantity2 = {2,2};
        
        orderController.placeOrder("Deon Chan", "email@domain.com",
                ResidenceType.NEW_COLLEGE.toString(), foodItems, foodQuantity, 10.00);
        orderController.placeOrder("Ben", "email@domain.com",
                ResidenceType.UNIVERSITY_COLLEGE.toString(), foodItems2, foodQuantity3, 20.03953590);
        orderController.placeOrder("Not Deon Chan", "email@domain.com",
                ResidenceType.NEW_COLLEGE.toString(), foodItems, foodQuantity2, 31.10);
        orderController.placeOrder("Vivian", "vivianyt.liu@utoronto.ca",
                ResidenceType.TRINITY_COLLEGE.toString(), foodItems, foodQuantity2, 20.31);

    }

}
