package use_cases_mains;

import chat_use_case.ChatDsGateway;
import chat_use_case.ChatInteractor;
import chat_use_case.ChatPresenter;
import chat_use_case.boundaries.ChatInputBoundary;
import screens.ChatScreen;

import java.io.IOException;

/**
 * Creates a chat, this is the main, or the 'entry point' to the chat.
 */
public class ChatMain {

    public static void create(String email1, String email2) throws IOException {
        ChatScreen chatScreen = new ChatScreen(email1, email2);
    }
}

