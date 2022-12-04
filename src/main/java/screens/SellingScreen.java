package screens;

import order_use_case.DoesNotExistException;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsModel;
import order_use_case.OrderFailed;
import selling_use_case.SellerMain;
import selling_use_case.SellingController;
import user_access_use_case.SignUpDsGateway;

import javax.swing.*;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

/**
 * This SellingScreen is the main screen that the Seller sees when they log in from the login page.
 * The seller can select and accept available orders from their selling hall.
 * The seller can log out or view past orders by using the buttons.
 * The seller can fulfill ongoing orders by selecting from the dropdown and clicking the 'Fulfill Selected Button'.
 */
public class SellingScreen extends JFrame {

    private JComboBox<String> currentOrdersDropdown;

    private JComboBox<String> sellerCurrentOrderDropdown;
    private final SellingController sellingController;
    private final String sellerEmail;
    private final String sellerName;

    private final String sellerResidence;

    private final SignUpDsGateway signUpGateway;

    private Integer orderToView;

    /**
     * The method is called when the 'Accept Order' button is clicked. It marks the order as ACCEPTED and store the
     * seller username and email in the orders.csv file.
     * @throws IOException
     */
    private void acceptClicked() throws IOException {

        try {
            String orderString = (String) currentOrdersDropdown.getSelectedItem();
            String[] orderInfoList = orderString.split(", ");
            String orderNumberString = orderInfoList[0];
            double price = Double.parseDouble(orderInfoList[1].substring(1));
            double balance = signUpGateway.getRequestModelFromEmail(sellerEmail).getMealPlanBalance();
            if (price > balance){
                JOptionPane.showMessageDialog(null,
                        "Please choose another order.", "Insufficient balance", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String buyerName = orderInfoList[2];

            sellingController.accept(sellerEmail, orderNumberString, sellerName);
            this.dispose();
            try {
                SellerMain.create(sellerEmail, sellerResidence, sellerName);
            } catch (DoesNotExistException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(null,
                    "Please communicate with " + buyerName + " to give them their food.",
                    "Order accepted.",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (OrderFailed ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Order failed.", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called when the 'Fulfilled Selected Order' is clicked. It sends the seller to the
     * SellerFulfillingOrderScreen to communicate and fulfill the selected order.
     * @throws IOException
     */
    private void orderClicked() throws IOException {
        String orderString = (String) sellerCurrentOrderDropdown.getSelectedItem();
        String[] orderInfoList = orderString.split(", ");
        this.orderToView = Integer.valueOf(orderInfoList[0]);
    }

    /**
     * Constructor for selling screen. Creates and sets up the JFrame for the screen.
     * @param sellingController the controller for the selling use case
     * @param signUpGateway the signUpGateway is an interface for the signUpGateway
     * @param orderDsGateway the orderDsGateway is an interface for the orderGateway
     * @param sellerEmail the seller's email
     * @param sellerResidence the seller's residence
     * @param sellerName the seller's name
     */
    public SellingScreen(SellingController sellingController, SignUpDsGateway signUpGateway, OrderDsGateway orderDsGateway, String sellerEmail, String
                         sellerResidence, String sellerName) {
        this.sellingController = sellingController;
        this.sellerEmail = sellerEmail;
        this.sellerResidence = sellerResidence;
        this.signUpGateway = signUpGateway;
        this.sellerName = sellerName;
        JPanel pnl = new JPanel(new GridLayout(5,1));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));

        // 'Log-out' by going back to the welcome screen
        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(actionEvent -> {
            this.setVisible(false);
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

        JButton pastOrderButton = new JButton("Past orders");
        pastOrderButton.addActionListener(actionEvent -> {
            try {
                new SellerPastOrdersScreen(orderDsGateway,sellerEmail);
            } catch (DoesNotExistException e) {
                throw new RuntimeException(e);
            }
        });

        buttonsPanel.add(logOutButton);
        buttonsPanel.add(pastOrderButton);
        pnl.add(buttonsPanel);

        JButton acceptButton = new JButton("Accept Order");
        acceptButton.addActionListener(actionEvent -> {
            this.setVisible(false);
            new ConfirmScreen(this, "Confirm accept order",
                    "Are you sure you want to accept this order?") {
                @Override
                void yesClicked() throws IOException {
                    JOptionPane.showMessageDialog(null, "You have successfully accepted this order.", "Order Accepted Successful", JOptionPane.PLAIN_MESSAGE);
                    this.dispose();
                    originalScreen.dispose();
                    acceptClicked();
                }
            };
        });

        JButton fulfillOrderButton = new JButton("Fulfill Selected Order");
        fulfillOrderButton.addActionListener(actionEvent -> {
            try {
                orderClicked();
                this.dispose();
                new SellerFulfillingOrderScreen(signUpGateway, orderDsGateway,sellerEmail,
                        orderDsGateway.getPriceFromOrderNumber(orderToView), orderToView);

            } catch (DoesNotExistException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        List<Integer> unfulfilledOrders = orderDsGateway.getUnfulfilledOrders(sellerResidence);
        String[] displayArray = new String[unfulfilledOrders.size()];
        for (int i = 0; i < unfulfilledOrders.size(); i++) {
            int orderNumber = unfulfilledOrders.get(i);
            OrderDsModel orderDsModel = orderDsGateway.getOrderInfo(orderNumber);
            String orderString = orderNumber + ", ";
            orderString += "$" + String.format("%.2f", orderDsModel.getPrice()) + ", ";
            orderString += orderDsModel.getBuyerName();

            for (int j = 0; j < orderDsModel.getFoodItems().length; j++){
                orderString += ", " + orderDsModel.getFoodItems()[j] + "(" + orderDsModel.getFoodQuantity()[j] + ")";
            }
            displayArray[i] = orderString;
        }

        if (unfulfilledOrders.size() > 0) {
            currentOrdersDropdown = new JComboBox<>(displayArray);
            LabelComboboxPanel ordersPanel = new LabelComboboxPanel(
                    new JLabel("Select an order:"), currentOrdersDropdown);
            pnl.add(ordersPanel);
            pnl.add(acceptButton);
        } else {
            JLabel noOrderLabel = new JLabel("There is currently no orders for this residence. ");
            noOrderLabel.setHorizontalAlignment(JLabel.CENTER);
            pnl.add(noOrderLabel);
            // acceptButton.setVisible(false);
        }

        //sus start
        List<Integer> currentOrders = orderDsGateway.getOrderNumbersFromSellerEmail(sellerEmail);
        System.out.println("current order numbers: ");
        for (int x : currentOrders) System.out.println(x);
        String[] displayCurrentArray = new String[currentOrders.size()];
        for (int i = 0; i < currentOrders.size(); i++) {
            int orderNumber = currentOrders.get(i);
            OrderDsModel orderDsModel = orderDsGateway.getOrderInfo(orderNumber);
            String orderString = orderNumber + ", ";
            orderString += "$" + String.format("%.2f", orderDsModel.getPrice()) + ", ";
            orderString += orderDsModel.getBuyerName();

            for (int j = 0; j < orderDsModel.getFoodItems().length; j++){
                orderString += ", " + orderDsModel.getFoodItems()[j] + "(" + orderDsModel.getFoodQuantity()[j] + ")";
            }
            displayCurrentArray[i] = orderString;
        }

        if (currentOrders.size() > 0) {
            sellerCurrentOrderDropdown = new JComboBox<>(displayCurrentArray);
            LabelComboboxPanel currentOrdersPanel = new LabelComboboxPanel(
                    new JLabel("Select one of your current order to view:"), sellerCurrentOrderDropdown);
            pnl.add(currentOrdersPanel);
            pnl.add(fulfillOrderButton);
        } else {
            JLabel noOrderLabel = new JLabel("Current you have no orders.");
            noOrderLabel.setHorizontalAlignment(JLabel.CENTER);
            pnl.add(noOrderLabel);
        }
        //bad end

        this.add(pnl);
        this.setTitle("Selling");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}
