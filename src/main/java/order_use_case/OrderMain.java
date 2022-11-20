package order_use_case;

import entities.OrderFactory;
import entities.ResidenceType;

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
        // TODO: OrderFactory is ENTIRELY UNUSED because it is completely unnecessary right now
        // Maybe we should add a method that calculates the price for us?
        OrderFactory orderFactory = new OrderFactory();
        OrderOutputBoundary orderPresenter = new OrderPresenter();
        OrderInputBoundary orderInteractor = new OrderInteractor(orders, orderPresenter, orderFactory);
        OrderController orderController = new OrderController(orderInteractor);
        String[] foodItems = {"Burger", "Fries"};
        Integer[] foodQuantity = {1,1};
        String[] foodItems2 = {"Burger", "Burger", "Poutine", "Burger"};
        Integer[] foodQuantity3 = {1,1,1,1};
        Integer[] foodQuantity2 = {2,2};
        
        orderController.placeOrder("Deon Chan", "email@domain.com", "New College", foodItems, foodQuantity, 10.00);
        orderController.placeOrder("Ben", "email@domain.com", "University College", foodItems2, foodQuantity3, 20.03953590);
        orderController.placeOrder("Not Deon Chan", "email@domain.com", "New College", foodItems, foodQuantity2, 31.10);
        orderController.placeOrder("Vivian", "vivianyt.liu@utoronto.ca", "Trinity College", foodItems, foodQuantity2, 20.31);

    }

}
