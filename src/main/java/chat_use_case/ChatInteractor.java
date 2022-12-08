package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;
import screens.ChatScreen;

import java.io.IOException;

/**
 * Interactor class for the chat use case
 * @author Aaron Ma
 */
public class ChatInteractor implements ChatInputBoundary {
    ChatDsBoundary ds;

    /**
     * The constructor for the chat interactor.
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    public ChatInteractor() throws IOException {
        ds = ChatDsGateway.getInstance();
    }

    /**
     * Creates a chat between two users
     * @param c The two users to create a chat between
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    public void createChat(ChatCreationRequestModel c) throws IOException {
        ds.createChat(c);
    }

    /**
     * Sends a message from one user to the other
     * @param m The necessary data: the sender and receiver, and the body of the message
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    public void sendMessage(ChatSendMessageModel m) throws IOException{
        ds.sendMessage(m);
    }

}