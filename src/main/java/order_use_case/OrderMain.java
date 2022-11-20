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
        String[] foodItems2 = {"Burger", "Burger", "Poutine", "Burger"};
        
        orderController.placeOrder("Deon Chan", "email@domain.com", "New College", foodItems, 10.00);
        orderController.placeOrder("Ben", "email@domain.com", "University College", foodItems2, 20.03953590);
        orderController.placeOrder("Not Deon Chan", "email@domain.com", "New College", foodItems, 31.10);
        orderController.placeOrder("Vivian", "vivianyt.liu@utoronto.ca", "Trinity College", foodItems, 20.31);

    }

}
