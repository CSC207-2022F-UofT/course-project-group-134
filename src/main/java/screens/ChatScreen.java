package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChatScreen extends JFrame{
    private final JTextField messageInput = new JTextField(15);

    private void sendClicked(ActionEvent actionEvent) throws IOException {

    }

    public ChatScreen() {
        JPanel pnl = new JPanel(new GridLayout(0,1));

        LabelTextPanel chatBox = new LabelTextPanel(
                new JLabel("Input message"), messageInput);
        pnl.add(chatBox);
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));

        JButton sendButton = new JButton("Send message");
        sendButton.addActionListener(actionEvent -> {
            try {
                sendClicked(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pnl.add(buttonsPanel);
        this.add(pnl);
        this.setTitle("Chat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);

    }
}
