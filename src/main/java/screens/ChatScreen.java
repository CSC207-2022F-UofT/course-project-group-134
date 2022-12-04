package screens;

import chat_use_case.ChatInteractor;
import chat_use_case.ChatPresenter;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.boundaries.ChatOutputBoundary;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;
import chat_use_case.models.ChatSendMessageModel;
import entities.*;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ChatScreen extends JFrame{
    private final JTextField messageInput = new JTextField(15);
    private JTextArea messageDisplay = new JTextArea();

    private ChatInputBoundary input;

    private ChatOutputBoundary output;

    private final String userEmail, recipiantEmail;
    private void sendClicked(ActionEvent actionEvent) throws IOException {

        String s = messageInput.getText();

        input.sendMessage(new ChatSendMessageModel(userEmail, recipiantEmail, s));
        messageInput.setText("");
        updateChatLog();

    }

    private void updateChatLog() throws IOException {
       ChatDataRecieveModel m =  output.getMessageList(new ChatLogRequestModel(userEmail, recipiantEmail));
        java.util.List<ChatMessage> c = m.getChatMessages();
        String chatlog = "";
        for(ChatMessage msg: c){
            chatlog = chatlog + msg.getSender().getEmail() + ": " +  msg.getContents() + "\n";
        }
        messageDisplay.setText(chatlog);

    }

    public ChatScreen(String userEmail, String recipiantEmail) throws IOException {
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
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
        output = new ChatPresenter();
        input = new ChatInteractor();

        this.userEmail = userEmail;
        this.recipiantEmail = recipiantEmail;
        updateChatLog();
    }
}
