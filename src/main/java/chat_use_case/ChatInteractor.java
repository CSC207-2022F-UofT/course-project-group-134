package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;
import screens.ChatScreen;

import java.io.IOException;

public class ChatInteractor implements ChatInputBoundary {
    ChatDsBoundary ds;
    public ChatInteractor() throws IOException {
        ds = ChatDsGateway.getInstance();
    }
    public void createChat(ChatCreationRequestModel c) throws IOException {
        ds.createChat(c);

    }
    public void sendMessage(ChatSendMessageModel m) throws IOException{
        ds.sendMessage(m);
    }

    public static class ChatMain {

        public static void create(String email1, String email2) throws IOException {
            ChatDsGateway chat;
            try {
                chat = ChatDsGateway.getInstance();
            } catch (IOException e) {
                throw new RuntimeException("Could not create file.");
            }
            ChatPresenter presenter = new ChatPresenter();
            ChatInputBoundary interactor = new ChatInteractor();
            ChatScreen chatScreen = new ChatScreen(email1, email2);
        }
    }
}