package screens.ChatScreenDevelopment;

import chat_use_case.ChatInteractor;
import chat_use_case.ChatPresenter;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.boundaries.ChatOutputBoundary;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;
import chat_use_case.models.ChatSendMessageModel;
import entities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChatScreen extends JFrame {

    private JTextArea chatHistory;

    private ChatInputBoundary ci;
    private ChatOutputBoundary co;

    private ArrayList<ChatMessage> messages;

    private User sender, receiver;
    private JTextField userMessageBox;
    private JButton sendMessageButton;
    private JButton confirmationButton;
    private JPanel chatPanel;
    private JScrollPane chatHistoryScrollPane;
    private JButton updateMessagesButton;


    public ChatScreen(User sender, User receiver) throws IOException {
        this.ci = new ChatInteractor();
        this.co = new ChatPresenter();
        this.sender = sender;
        this.receiver = receiver;
        //We initialize the messages/message history when we initialize a new ChatScreen.
        this.messages = co.getMessageList(new ChatLogRequestModel(sender, receiver)).getChatMessages();

        this.chatHistory.setLineWrap(true);
        this.chatHistory.setWrapStyleWord(true);
        this.chatHistory.setEditable(false);
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputMessage = userMessageBox.getText();
                userMessageBox.setText(null);
                ci.sendMessage(new ChatSendMessageModel(sender, receiver, inputMessage));
                updateChat();
                //TODO: Which one is this current user? How can we differentiate?
            }
        });
        confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmOrderComplete();
            }
        });

        updateMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChat();
            }
        });
        this.setSize(400,400);
    }

    private void updateChat(){
        ChatLogRequestModel request = new ChatLogRequestModel(sender, receiver);
        this.messages = co.getMessageList(request).getChatMessages();//TODO: discussy the sussy. Should I use the 'this' keyword?
        displayChat();
    }

    private void displayChat(){
        StringBuilder totalMessages = new StringBuilder();
        for (int i = 0; i < this.messages.size(); i++){
            totalMessages.append(this.messages.get(i).getSender().getUsername()).append(" ").append(this.messages.get(i).getContents()).append(System.lineSeparator());
        }

        this.chatHistory.setText(totalMessages.toString());

    }

    private void confirmOrderComplete(){
        //TODO: how do we confirm for each user that the order has been completed? How do we then send this user back to buyer screen?
        //This seems like there is something missing in the chat interactor: we need functionality for this.
    }




//    //testy thing
//    public static void main(String[]args){
//        new ChatScreen().setVisible(true);
//
//    }
}