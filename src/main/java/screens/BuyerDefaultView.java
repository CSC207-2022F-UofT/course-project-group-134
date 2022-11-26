package screens;

import get_menus_use_case.GetMenusMain;
import order_history_use_case.OrderHistoryController;
import order_history_use_case.OrderHistoryInputBoundary;
import order_history_use_case.OrderHistoryInteractor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BuyerDefaultView extends JFrame {

    private JPanel pnl = new JPanel(new GridLayout(2, 1));
    private JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    private JButton placeOrderButton = new JButton("Place new order");
    private JButton logoutButton = new JButton("Logout");
    private JPanel topPanel = new JPanel(new GridLayout(1, 2));
    private JPanel getOrderHistoryPanel;
    private JPanel currentOrdersInnerPanel;
    JScrollPane orderHistoryPanel;
    JScrollPane currentOrdersPanel;
    private OrderHistoryController orderHistoryController;
    private String username;
    private String email;

    public BuyerDefaultView(String username, String email, OrderHistoryInputBoundary orderHistoryInteractor){
        this.username =username;
        this.email = email;
        this.orderHistoryController = new OrderHistoryController(username, email, orderHistoryInteractor);
        topPanel.add(placeOrderButton);
        topPanel.add(logoutButton);
        pnl.add(topPanel);

        placeOrderButton.addActionListener(actionEvent -> {
            try {
                placeNewOrderClicked(username, email);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        logoutButton.addActionListener(actionEvent -> {
            this.dispose();
            try {
                new WelcomeScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        tabbedPane.addTab("Order History", orderHistoryPanel);
        tabbedPane.addTab("Current Orders", currentOrdersPanel);
        this.createOrderHistoryPanel();
        this.createCurrentOrdersPanel();

        pnl.add(tabbedPane);
        this.add(pnl);

        this.setTitle("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }

    private void createOrderHistoryPanel(){
        ArrayList<String[]> orderHistory =  this.orderHistoryController.returnFinishedOrders();
        getOrderHistoryPanel = new JPanel(new GridLayout(orderHistory.size(),2));
        OrdersInfoHeaders[] ordersInfoHeaders = OrdersInfoHeaders.values();
        for (String[] tempOrder : orderHistory) {
            JPanel tempOrderPanel = new JPanel(new GridLayout(tempOrder.length + 1, 1));
            for (int i = 0; i < tempOrder.length; i++) {
                tempOrderPanel.add(new JLabel(ordersInfoHeaders[i] + ": "+ tempOrder[i]));
            }
            tempOrderPanel.add(new JLabel(" "));
            this.getOrderHistoryPanel.add(tempOrderPanel);
            JButton reviewButton = new JButton("Review");

            reviewButton.addActionListener(actionEvent -> {
                new PreReviewView(tempOrder[7], tempOrder[5], username);
            });

            this.getOrderHistoryPanel.add(reviewButton);
        }
        this.orderHistoryPanel = new JScrollPane(getOrderHistoryPanel);
        tabbedPane.setComponentAt(0, this.orderHistoryPanel);
    }

    private void createCurrentOrdersPanel(){

        ArrayList<String[]> currentOrders =  this.orderHistoryController.returnCurrentOrders();
        currentOrdersInnerPanel = new JPanel(new GridLayout(currentOrders.size(),1));
        OrdersInfoHeaders[] ordersInfoHeaders = OrdersInfoHeaders.values();
        for (String[] tempOrder : currentOrders) {
            JPanel tempOrderPanel = new JPanel(new GridLayout(tempOrder.length + 1, 1));
            for (int i = 0; i < tempOrder.length; i++) {
                tempOrderPanel.add(new JLabel(ordersInfoHeaders[i] + ": "+ tempOrder[i]));
            }
            tempOrderPanel.add(new JLabel(" "));
            this.currentOrdersInnerPanel.add(tempOrderPanel);
        }
        this.currentOrdersPanel = new JScrollPane(currentOrdersInnerPanel);
        tabbedPane.setComponentAt(1, this.currentOrdersPanel);
    }

    public void placeNewOrderClicked(String username, String email) throws Exception {
        GetMenusMain.create(username, email);
        this.dispose();
    }
}
