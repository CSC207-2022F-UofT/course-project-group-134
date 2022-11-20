package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatOutputBoundary;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;

public class ChatPresenter implements ChatOutputBoundary {


    ChatDsBoundary ds;

    @Override
    public ChatDataRecieveModel getMessageList(ChatLogRequestModel m) {
        return ds.getMessageList(m);
    }
}
