package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;

import java.io.IOException;

public interface ChatInputBoundary {

    public void createChat(ChatCreationRequestModel m) throws IOException;

    public void sendMessage(ChatSendMessageModel m) throws IOException;

}
