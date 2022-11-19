package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationModel;
import chat_use_case.models.ChatRecieveMessagesModel;
import chat_use_case.models.ChatSendMessageModel;

public interface ChatInputBoundary {

    public void createChat(ChatCreationModel m);

    public void sendMessage(ChatSendMessageModel m);

}
