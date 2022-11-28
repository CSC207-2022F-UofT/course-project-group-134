package chat_use_case.models;

import chat_use_case.ChatDsGateway;
import chat_use_case.ChatInteractor;
import chat_use_case.ChatPresenter;
import chat_use_case.boundaries.ChatInputBoundary;
import entities.BuyerFactory;
import entities.SellerFactory;
import entities.UserFactory;
import screens.ChatScreen;
import screens.SignupScreen;
import user_access_use_case.*;

import java.io.IOException;

public class ChatMain {

    public static void create() throws IOException {
        ChatDsGateway chat;
        try {
            chat = ChatDsGateway.getInstance();
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        ChatPresenter presenter = new ChatPresenter();
        ChatInputBoundary interactor = new ChatInteractor();
        ChatScreen chatScreen = new ChatScreen();
    }
}
