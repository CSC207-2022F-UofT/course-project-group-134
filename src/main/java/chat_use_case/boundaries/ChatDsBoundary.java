package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;
import chat_use_case.models.ChatSendMessageModel;

import java.io.IOException;

/**
 * The Data Storage boundary interface.
 */
public interface ChatDsBoundary {
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
    /**
     * This retrieves the list of messages from the database
     * @param m information needed to retrieve a list of messages (the two users)
     * @return The list of messages, in the form of a ChatDataRecieveModel
     */
    ChatDataRecieveModel getMessageList(ChatLogRequestModel m);

}
