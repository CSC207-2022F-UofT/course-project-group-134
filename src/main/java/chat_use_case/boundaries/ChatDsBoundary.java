package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatLogRequestModel;
import chat_use_case.models.ChatSendMessageModel;

import java.io.IOException;

public interface ChatDsBoundary {


    public void createChat(ChatCreationRequestModel m) throws IOException;

    public void sendMessage(ChatSendMessageModel m) throws IOException;

    public ChatDataRecieveModel getMessageList(ChatLogRequestModel m);


}
