package screens;

import chat_use_case.ChatInteractor;
import chat_use_case.ChatPresenter;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.boundaries.ChatOutputBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import entities.*;
import order_use_case.BuyerMain;
import order_use_case.DoesNotExistException;
import selling_use_case.SellerMain;
import user_login_use_case.LoginController;
import user_login_use_case.LoginFailed;
import user_login_use_case.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChatScreen extends JFrame {


    private JTextField chatHistory;
    private JTextArea sendbox;

    private JButton send;

    private ChatInputBoundary ci;
    private ChatOutputBoundary co;

    private User sender, receiver;


    public ChatScreen(){
        this(null,null);
    }
    public ChatScreen(User sender, User receiver){
        this.ci = new ChatInteractor();
        this.co = new ChatPresenter();
        this.sender = sender;
        this.receiver = receiver;

        this.chatHistory = new JTextField(30);
        this.sendbox = new JTextArea();
        this.send = new JButton("send");


        this.setSize(400,400);


        JPanel a = new JPanel();



    }








    //testy thing
    public static void main(String[]args){
        new ChatScreen().setVisible(true);

    }
}