package chat_use_case.boundaries;

import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;

import java.io.IOException;

public interface ChatInputBoundary {

    void createChat(ChatCreationRequestModel m) throws IOException;

    void sendMessage(ChatSendMessageModel m) throws IOException;

}
