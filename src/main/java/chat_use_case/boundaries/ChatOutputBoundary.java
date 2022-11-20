package chat_use_case.boundaries;

import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;

public interface ChatOutputBoundary {

    public ChatDataRecieveModel getMessageList(ChatLogRequestModel m);

}
