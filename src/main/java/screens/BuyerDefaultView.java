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

    public BuyerDefaultView(String username, String email, OrderHistoryInputBoundary orderHistoryInteractor){
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


        this.createOrderHistoryPanel();
        this.createCurrentOrdersPanel();

        tabbedPane.addTab("Order History", orderHistoryPanel);
        tabbedPane.addTab("Current Orders", currentOrdersPanel);

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
        getOrderHistoryPanel = new JPanel(new GridLayout(orderHistory.size(),1));
        OrdersInfoHeaders[] ordersInfoHeaders = OrdersInfoHeaders.values();
        for (String[] tempOrder : orderHistory) {
            JPanel tempOrderPanel = new JPanel(new GridLayout(tempOrder.length + 1, 1));
            for (String s : tempOrder) {
                tempOrderPanel.add(new JLabel(ordersInfoHeaders[Arrays.asList(tempOrder).indexOf(s)] + ": "+ s));
            }
            tempOrderPanel.add(new JLabel(" "));
            this.getOrderHistoryPanel.add(tempOrderPanel);
        }
        this.orderHistoryPanel = new JScrollPane(getOrderHistoryPanel);
        tabbedPane.setComponentAt(0, this.orderHistoryPanel);
    }

    private void createCurrentOrdersPanel(){
        ArrayList<String[]> orderHistory =  this.orderHistoryController.returnCurrentOrders();
        currentOrdersInnerPanel = new JPanel(new GridLayout(1,1));
        OrdersInfoHeaders[] ordersInfoHeaders = OrdersInfoHeaders.values();
        for (String[] tempOrder : orderHistory) {
            JPanel tempOrderPanel = new JPanel(new GridLayout(tempOrder.length + 1, 1));
            for (String s : tempOrder) {
                tempOrderPanel.add(new JLabel(ordersInfoHeaders[Arrays.asList(tempOrder).indexOf(s)] + ": " + (s.equals("null") ? "None":s)));
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
