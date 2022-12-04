package screens;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class ConfirmScreen extends JFrame {
    private JPanel pnl = new JPanel(new GridLayout(2, 1));
    private JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
    private JButton yesButton = new JButton("Yes");
    private JButton noButton = new JButton("No");
    private JLabel message;
    public JFrame originalScreen;


    public ConfirmScreen(JFrame originalScreen, String title, String prompt) {
        this.message = new JLabel(prompt);
        this.originalScreen = originalScreen;
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
        this.setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(400, 0);
        this.setVisible(true);
    }

    abstract void yesClicked() throws IOException;

    private void noClicked(){
        originalScreen.setVisible(true);
        this.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
