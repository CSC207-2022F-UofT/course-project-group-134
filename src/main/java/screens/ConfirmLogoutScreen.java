package screens;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ConfirmLogoutScreen extends JFrame {
    private JPanel pnl = new JPanel(new GridLayout(2, 1));
    private JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
    private JButton yesButton = new JButton("Yes");
    private JButton noButton = new JButton("No");
    private JLabel message = new JLabel("Are you sure you want to logout?");
    private BuyerDefaultView buyerDefaultView;


    public ConfirmLogoutScreen(BuyerDefaultView buyerDefaultView){
        this.buyerDefaultView = buyerDefaultView;
        pnl.add(message);
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);
        pnl.add(buttonsPanel);

        yesButton.addActionListener(actionEvent -> {
            try {
                yesClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        noButton.addActionListener(actionEvent -> {
            noClicked();
        });

        this.add(pnl);
        this.setTitle("Confirm Logout");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(400, 0);
        this.setVisible(true);
    }

    private void yesClicked() throws IOException {
        JOptionPane.showMessageDialog(null, "You have successfully logged out.", "Logout Successful", JOptionPane.PLAIN_MESSAGE);
        buyerDefaultView.dispose();
        this.dispose();
        new WelcomeScreen();
    }

    private void noClicked(){
        buyerDefaultView.setVisible(true);
        this.dispose();
    }

    @Override
    public void dispose() {
        this.buyerDefaultView.logoutScreenClosed = true;
        super.dispose();
    }
}
