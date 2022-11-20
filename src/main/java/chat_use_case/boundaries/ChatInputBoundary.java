package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;

public interface ChatInputBoundary {

    public void createChat(ChatCreationRequestModel m);

    public void sendMessage(ChatSendMessageModel m);

}
