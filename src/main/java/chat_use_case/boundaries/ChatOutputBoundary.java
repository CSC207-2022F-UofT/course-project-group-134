package chat_use_case.boundaries;

import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;

import java.io.IOException;

public interface ChatOutputBoundary {

    ChatDataRecieveModel getMessageList(ChatLogRequestModel m) throws IOException;

}
