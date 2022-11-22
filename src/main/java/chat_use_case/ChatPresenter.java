package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatOutputBoundary;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;

import java.io.IOException;

public class ChatPresenter implements ChatOutputBoundary {


    ChatDsBoundary ds;

    public ChatPresenter() throws IOException {
        ds = ChatDsGateway.getInstance();
    }

    @Override
    public ChatDataRecieveModel getMessageList(ChatLogRequestModel m) throws IOException{
        return ds.getMessageList(m);
    }
}
