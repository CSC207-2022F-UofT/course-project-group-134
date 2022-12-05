package chat_use_case.boundaries;

import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;

import java.io.IOException;

/**
 * The output boundary for the chat use case
 */
public interface ChatOutputBoundary {
    /**
     * Gets message list for the given information
     * @param m The request model that represents the two users that we are requesting information for
     * @return The model that represents the list of chat messages exchanged between the two users
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    ChatDataRecieveModel getMessageList(ChatLogRequestModel m) throws IOException;

}
