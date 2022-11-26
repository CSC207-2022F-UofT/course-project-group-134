package screens;

import chat_use_case.models.ChatMain;
import order_use_case.DoesNotExistException;
import selling_use_case.SellerMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChatScreen extends JFrame{
    private final JTextField messageInput = new JTextField(15);
    private JTextArea messageDisplay = new JTextArea();

    private void sendClicked(ActionEvent actionEvent) throws IOException {

    }

    public ChatScreen() {
        JPanel pnl = new JPanel(new GridLayout(0,1));
        pnl.add(messageDisplay);
        messageDisplay.setEditable(false);

        LabelTextPanel chatBox = new LabelTextPanel(
                new JLabel("Input message"), messageInput);
        pnl.add(chatBox);


        JButton sendButton = new JButton("Send message");
        sendButton.addActionListener(actionEvent -> {
            try {
                sendClicked(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pnl.add(sendButton);
        this.add(pnl);
        this.setTitle("Chat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);

    }
}
