package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;

import java.io.IOException;

public class ChatInteractor implements ChatInputBoundary {
    ChatDsBoundary ds;
    public ChatInteractor() throws IOException {
        ds = ChatDsGateway.getInstance();
    }
    public void createChat(ChatCreationRequestModel c) throws IOException {
        ds.createChat(c);

    }
    public void sendMessage(ChatSendMessageModel m) throws IOException{
        ds.sendMessage(m);
    }
}