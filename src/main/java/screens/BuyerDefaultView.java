package screens;

import chat_use_case.ChatInteractor;
import entities.OrderStatusType;
import get_menus_use_case.GetMenusMain;
import order_history_use_case.OrderHistoryController;
import order_history_use_case.OrderHistoryInputBoundary;
import order_use_case.OrderDsGateway;
import order_use_case.OrderGateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private OrderDsGateway orders;

    private void chatClicked(ActionEvent actionEvent) throws IOException {
        ChatInteractor.ChatMain.create();
    }

    public BuyerDefaultView(String username, String email, OrderHistoryInputBoundary orderHistoryInteractor){
        try {
            orders = new OrderGateway("./src/main/java/data_storage/orders.csv");
            //System.out.println("File Created!");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }

        this.username = username;
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
        // This must come later
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
        //System.out.println(currentOrders.toString());
        for (String[] tempOrder : currentOrders) {
            JPanel orderPanel = new JPanel(new GridLayout(1,2));
            JPanel tempOrderPanel = new JPanel(new GridLayout(tempOrder.length + 1, 1));

            for (String s : tempOrder) {
                //int currentIndex = Arrays.asList(tempOrder).indexOf(s);
                if (!s.equals("null")) {
                    tempOrderPanel.add(new JLabel(ordersInfoHeaders[Arrays.asList(tempOrder).indexOf(s)] + ": " + s));
                }

            }
            tempOrderPanel.add(new JLabel(" "));
            orderPanel.add(tempOrderPanel);

            OrderStatusType orderStatus = OrderStatusType.valueOf(tempOrder[6]);
            if (orderStatus != OrderStatusType.ORDERED) {
                JPanel rightPanel = new JPanel(new GridLayout(2,1));
                JButton chatButton = new JButton("Chat");
                chatButton.addActionListener(actionEvent -> {
                    try {
                        chatClicked(actionEvent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                rightPanel.add(chatButton);
                JButton buyerConfirmButton = new JButton("Confirm Delivery");
                rightPanel.add(buyerConfirmButton);
                orderPanel.add(rightPanel);
                buyerConfirmButton.addActionListener(actionEvent -> {
                    if (orderStatus == OrderStatusType.SELLER_CONFIRMED) {
                        this.orders.setOrderStatus(Integer.valueOf(tempOrder[0]), OrderStatusType.FINISHED);
                        JOptionPane.showMessageDialog(null, "Successfully finished order.", "Order Finished", JOptionPane.PLAIN_MESSAGE);
                        this.createOrderHistoryPanel();
                        this.createCurrentOrdersPanel();
                        this.revalidate();
                        this.repaint();
                        System.out.println("REMOVE ORDER FROM VIEW LIST???");
                    } else {
                        this.orders.setOrderStatus(Integer.valueOf(tempOrder[0]), OrderStatusType.BUYER_CONFIRMED);
                        JOptionPane.showMessageDialog(null, "Successfully confirmed order.", "Order Confirmed", JOptionPane.PLAIN_MESSAGE);
                        this.createCurrentOrdersPanel();
                        this.revalidate();
                        this.repaint();
                        System.out.println("Change status please.");
                    }
                });
            }

            this.currentOrdersInnerPanel.add(orderPanel);
        }
        this.currentOrdersPanel = new JScrollPane(currentOrdersInnerPanel);
        tabbedPane.setComponentAt(1, this.currentOrdersPanel);
    }

    public void placeNewOrderClicked(String username, String email) throws Exception {
        GetMenusMain.create(username, email);
        this.dispose();
    }
}
