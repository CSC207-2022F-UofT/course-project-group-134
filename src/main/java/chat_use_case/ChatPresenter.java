package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatOutputBoundary;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;

import java.io.IOException;

/**
 * Presenter class for the Chat use case.
 *
 * @author Aaron Ma
 */
public class ChatPresenter implements ChatOutputBoundary {


    ChatDsBoundary ds;

    /**
     * Constructor for this class.
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    public ChatPresenter() throws IOException {
        ds = ChatDsGateway.getInstance();
    }

    /**
     * Gets message list for the given information
     * @param m The request model that represents the two users that we are requesting information for
     * @return The model that represents the list of chat messages exchanged between the two users
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    @Override
    public ChatDataRecieveModel getMessageList(ChatLogRequestModel m) throws IOException{
        return ds.getMessageList(m);
    }
}
