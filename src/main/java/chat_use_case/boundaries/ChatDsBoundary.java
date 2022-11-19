package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatLogRecieveModel;
import chat_use_case.models.ChatLogRequestModel;
import chat_use_case.models.ChatSendMessageModel;

public interface ChatDsBoundary {


    public void createChat(ChatCreationRequestModel m);

    public void sendMessage(ChatSendMessageModel m);

    public ChatLogRecieveModel getMessageList(ChatLogRequestModel m);


}
