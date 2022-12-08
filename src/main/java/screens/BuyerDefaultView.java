package screens;

import chat_use_case.ChatInteractor;
import entities.OrderStatusType;
import order_history_use_case.OrderHistoryController;
import order_history_use_case.OrderHistoryInputBoundary;

import use_cases_mains.BuyerMain;

import order_use_case.OrderDsGateway;
import order_use_case.OrderGateway;
import use_cases_mains.ChatMain;
import use_cases_mains.GetMenusMain;

import javax.swing.*;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BuyerDefaultView extends JFrame {

    /**
     *The main JPanel for this view.  The two rows are for:
     * 1) placing an order/logging out
     * 2) the list of past orders and current orders
     */
    private JPanel pnl = new JPanel(new GridLayout(2, 1));
    /**
     * A JTabbedPane with two tabs (with a scrolling layout- i.e., the pages can be scrolled up/down):
     * 1) the list of past orders
     * 2) the list of current orders
     */
    private final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    /**
     * A button which takes the user to the page for placing an order (this is the OrderView
     * and involves the GetMenus use case)
     */
    private final JButton placeOrderButton = new JButton("Place new order");
    /**
     * Clicking this button logs out the user and takes them back to the Welcome Screen
     */
    private final JButton logoutButton = new JButton("Logout");
    /**
     * The first row of pnl.  Includes the LOGOUT button and PLACE ORDER button
     */
    private final JPanel topPanel = new JPanel(new GridLayout(1, 2));
    /**
     * The left half of the tabbedPane - shows the user their past orders
     */
    private JScrollPane orderHistoryPanel;
    /**
     * The right half of the tabbedPane - shows the user their current orders
     */
    private JScrollPane currentOrdersPanel;
    /**
     * The inner part of orderHistoryPanel.  This is embedded into the JScrollPane and this is where
     * the information about the orders resides.  The ScrollPane is just used to enable the scrolling feature
     */
    private JPanel orderHistoryInnerPanel;
    /**
     * The inner part of currentOrdersPanel.  Similar to orderHistoryInnerPanel
     */
    private JPanel currentOrdersInnerPanel;
    /**
     * The Controller for the Order History use case.  Used for loading info into the orderHistoryInnerPanel
     */
    private final OrderHistoryController orderHistoryController;
    /**
     * The username of the currently logged-in user
     */
    private final String username;
    /**
     * The email id of the currently logged-in user
     */
    private final String email;
    /**
     * The Gateway for the Order use case.  Used when the user wants to make a new order.
     */
    private final OrderDsGateway orders;

    /**
     *The below method is the constructor for this class.  It creates some important buttons and action
     * listeners for them.  Also initializes the tabbed panes and scroll panes
     * @param username The username of the currently logged-in user
     * @param email The email-id of the currently logged-in user
     * @param orderHistoryInteractor The Use Case Interactor to be used for the OrderHistory use case
     */
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
           this.setVisible(false); // To prevent user from clicking on logout button multiples times
           new ConfirmScreen(this, "Confirm Logout",
                    "Are you sure you want to logout?") {
                @Override
                void yesClicked() throws IOException {
                    JOptionPane.showMessageDialog(null, "You have successfully logged out.", "Logout Successful", JOptionPane.PLAIN_MESSAGE);
                    this.dispose();
                    originalScreen.dispose();
                    new WelcomeScreen();
                }
            };
        });

        // creating the two tabs (the panels added are scroll panes)
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

    /**
     * The below method creates the "Order History" tab in the buyer's view.  It uses the orderHistoryController
     * to accomplish this (the order history is obtained from the controller, which calls other classes from that
     * use case)
     */
    private void createOrderHistoryPanel(){
        List<String[]> orderHistory =  this.orderHistoryController.returnFinishedOrders();
        orderHistoryInnerPanel = new JPanel(new GridLayout(orderHistory.size(),2));

        OrdersInfoHeaders[] ordersInfoHeaders = OrdersInfoHeaders.values();
        for (String[] tempOrder : orderHistory) {
            JPanel tempOrderPanel = new JPanel(new GridLayout(tempOrder.length + 1, 1));
            for (int i = 0; i < tempOrder.length; i++) {
                tempOrderPanel.add(new JLabel(ordersInfoHeaders[i] + ": "+ tempOrder[i]));
            }
            tempOrderPanel.add(new JLabel(" "));
            this.orderHistoryInnerPanel.add(tempOrderPanel);
            JButton reviewButton = new JButton("Review");

            reviewButton.addActionListener(actionEvent -> {
                this.dispose();
                new PreReviewView(tempOrder[7], tempOrder[5], username, email);
            });

            this.orderHistoryInnerPanel.add(reviewButton);
        }
        this.orderHistoryPanel = new JScrollPane(orderHistoryInnerPanel);
        tabbedPane.setComponentAt(0, this.orderHistoryPanel);
    }

    /**
     * Similar to order history, but instead for current orders (orders that are not yet marked as "FINISHED"
     * in the csv file)
     */
    private void createCurrentOrdersPanel(){
        List<String[]> currentOrders =  this.orderHistoryController.returnCurrentOrders();
        currentOrdersInnerPanel = new JPanel(new GridLayout(currentOrders.size(),1));
        OrdersInfoHeaders[] ordersInfoHeaders = OrdersInfoHeaders.values();
        String csvPath = "./src/main/java/data_storage/orders.csv";

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
                        ChatMain.create(email, tempOrder[4]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                rightPanel.add(chatButton);
                JButton buyerConfirmButton = new JButton("Confirm Delivery");
                rightPanel.add(buyerConfirmButton);
                if (orderStatus == OrderStatusType.BUYER_CONFIRMED) {
                    buyerConfirmButton.setVisible(false);
                }
                orderPanel.add(rightPanel);
                buyerConfirmButton.addActionListener(actionEvent -> {
                    if (orderStatus == OrderStatusType.SELLER_CONFIRMED) {
                        this.orders.setOrderStatus(Integer.parseInt(tempOrder[0]), OrderStatusType.FINISHED);
                        JOptionPane.showMessageDialog(null, "Successfully finished order.", "Order Finished", JOptionPane.PLAIN_MESSAGE);
                        //this.createOrderHistoryPanel();
                        //this.createCurrentOrdersPanel();
                        try {
                            BuyerMain.create(username, email, csvPath);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        this.dispose();
                        //System.out.println("REMOVE ORDER FROM VIEW LIST???");
                    } else {
                        this.orders.setOrderStatus(Integer.parseInt(tempOrder[0]), OrderStatusType.BUYER_CONFIRMED);
                        JOptionPane.showMessageDialog(null, "Successfully confirmed order.", "Order Confirmed", JOptionPane.PLAIN_MESSAGE);
                        this.dispose();
                        try {
                            BuyerMain.create(username, email, csvPath);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        //System.out.println("Change status please.");
                    }
                });
            }

            this.currentOrdersInnerPanel.add(orderPanel);
        }
        this.currentOrdersPanel = new JScrollPane(currentOrdersInnerPanel);
        tabbedPane.setComponentAt(1, this.currentOrdersPanel);
    }

    /**
     * Cal
     * @param username
     * @param email
     * @throws Exception
     */
    public void placeNewOrderClicked(String username, String email) throws Exception {
        GetMenusMain.create(username, email);
        this.dispose();
    }
}
