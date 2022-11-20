package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;

public class ChatInteractor implements ChatInputBoundary {
    ChatDsBoundary ds;
    public ChatInteractor(){
        ds = ChatDsGateway.getInstance();
    }
    public void createChat(ChatCreationRequestModel c) {
        ds.createChat(c);

    }
    public void sendMessage(ChatSendMessageModel m){
        ds.sendMessage(m);
    }
}