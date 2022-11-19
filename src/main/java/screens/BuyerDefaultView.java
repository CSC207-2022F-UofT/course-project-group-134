package screens;

import get_menus_use_case.GetMenusMain;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BuyerDefaultView extends JFrame {

    private JPanel pnl = new JPanel(new GridLayout(2, 1));
    private JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    private JButton placeOrderButton = new JButton("Place new order");
    private JButton logoutButton = new JButton("Logout");
    private JPanel topPanel = new JPanel(new GridLayout(1, 2));
    JPanel orderHistoryPanel;
    JPanel currentOrdersPanel;

    public BuyerDefaultView(String username, String email){
        topPanel.add(placeOrderButton);
        topPanel.add(logoutButton);
        pnl.add(topPanel);

        placeOrderButton.addActionListener(actionEvent -> {
            try {
                placeNewOrderClicked();
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
        pnl.add(tabbedPane);
        this.add(pnl);

        this.setTitle("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }

    private void createOrderHistoryPanel(){

    }

    private void createCurrentOrdersPanel(){

    }

    public void placeNewOrderClicked() throws Exception {
        GetMenusMain.create();
        this.dispose();
    }
}
