package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;

import java.io.IOException;

/**
 * The input boundary for the chat use case.
 */
public interface ChatInputBoundary {
    /**
     * Creates a chat
     * @param m Request model for the information needed to create chat(sender and receiver).
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    void createChat(ChatCreationRequestModel m) throws IOException;
    /**
     * Sends a message, updates the database accordingly
     * @param m The message information (sender, receiver, message text)
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    void sendMessage(ChatSendMessageModel m) throws IOException;

}
