package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatSendMessageModel;

public class ChatInteractor implements ChatInputBoundary {
    ChatDsBoundary dataStore;


    public ChatInteractor(){
        dataStore = ChatDsGateway.getInstance();
    }

    public void createChat(ChatCreationRequestModel c) {
        dataStore.createChat(c);

    }

    public void sendMessage(ChatSendMessageModel m){
        dataStore.sendMessage(m);
    }
}