package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;
import chat_use_case.models.ChatSendMessageModel;

import java.io.IOException;

public interface ChatDsBoundary {

    void createChat(ChatCreationRequestModel m) throws IOException;

    void sendMessage(ChatSendMessageModel m) throws IOException;

    ChatDataRecieveModel getMessageList(ChatLogRequestModel m);

}
