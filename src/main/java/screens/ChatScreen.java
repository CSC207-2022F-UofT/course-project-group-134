package screens;

import chat_use_case.ChatInteractor;
import chat_use_case.ChatPresenter;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.boundaries.ChatOutputBoundary;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;
import chat_use_case.models.ChatSendMessageModel;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChatScreen extends JFrame{
    private final JTextField messageInput = new JTextField(15);
    private JTextArea messageDisplay = new JTextArea();
    private JScrollPane messageScrollPane = new JScrollPane(messageDisplay);

    private ChatInputBoundary input;

    private ChatOutputBoundary output;

    private final String userEmail, recipientEmail;
    private void sendClicked(ActionEvent actionEvent) throws IOException {

        String s = messageInput.getText();

        input.sendMessage(new ChatSendMessageModel(userEmail, recipientEmail, s));
        messageInput.setText("");
        updateChatLog();

    }

    private void updateChatLog() throws IOException {
        ChatDataRecieveModel m =  output.getMessageList(new ChatLogRequestModel(userEmail, recipientEmail));
        java.util.List<ChatMessage> c = m.getChatMessages();
        StringBuilder chatLog = new StringBuilder("");
        for(ChatMessage msg: c){
            chatLog.append(msg.getSender().getEmail() + ": " + msg.getContents() + System.lineSeparator());
        }
        String chatLogString = chatLog.toString();
        messageDisplay.setText(chatLogString);

    }

    public ChatScreen(String userEmail, String recipientEmail) throws IOException {
        JPanel pnl = new JPanel(new GridLayout(0,1));
        pnl.add(messageScrollPane);
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
        this.recipientEmail = recipientEmail;
        updateChatLog();
    }
}
