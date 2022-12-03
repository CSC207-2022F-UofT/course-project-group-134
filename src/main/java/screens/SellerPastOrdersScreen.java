package screens;

import order_use_case.DoesNotExistException;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsModel;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.List;

/**
 * The screen for viewing the past orders. It allows the seller to close it when not needed.
 */
public class SellerPastOrdersScreen extends JFrame{

    /**
     * Constructor for sellerPastOrder screen. Creates and sets up the JFrame for the screen.
     * @param orderDsGateway orderDsGateway is an interface for the orderGateway
     * @param sellerEmail seller's email
     * @throws DoesNotExistException
     */
    public SellerPastOrdersScreen(OrderDsGateway orderDsGateway, String sellerEmail
    ) throws DoesNotExistException {

        JPanel orderPanel = new JPanel(new GridLayout(0, 1));

        List<Integer> finishedOrders = orderDsGateway.getFinishedOrders(sellerEmail);

        if (finishedOrders.size() == 0) {
            JLabel noOrdersLabel = new JLabel("You have no finished orders.");
            noOrdersLabel.setHorizontalAlignment(JLabel.CENTER);
            orderPanel.add(noOrdersLabel);
        }
        for (int orderNumber: finishedOrders) {
            OrderDsModel orderDsModel = orderDsGateway.getOrderInfo(orderNumber);
            JLabel orderNumberLabel = new JLabel("Order #: " + orderNumber);
            orderNumberLabel.setHorizontalAlignment(JLabel.CENTER);
            orderPanel.add(orderNumberLabel);

            JLabel buyerNameLabel = (new JLabel("Buyer: " + orderDsModel.getBuyerName()));
            buyerNameLabel.setHorizontalAlignment(JLabel.CENTER);
            orderPanel.add(buyerNameLabel);

            String foodItems = "";
            String[] foodItemArray = orderDsModel.getFoodItems();
            for (int i = 0; i < foodItemArray.length; i++) {
                String foodItem = foodItemArray[i];
                if (i > 0) foodItems += ", ";
                foodItems += foodItem;
            }

            JLabel foodItemsLabel = new JLabel("Food Items:" + foodItems);
            foodItemsLabel.setHorizontalAlignment(JLabel.CENTER);
            orderPanel.add(foodItemsLabel);
        }

        this.add(orderPanel);
        this.setTitle("Past Orders");
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}
