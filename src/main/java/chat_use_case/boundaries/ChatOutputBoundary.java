package chat_use_case.boundaries;

import chat_use_case.models.ChatLogRecieveModel;
import chat_use_case.models.ChatLogRequestModel;

public interface ChatOutputBoundary {

    public ChatLogRecieveModel getMessageList(ChatLogRequestModel m);

}
