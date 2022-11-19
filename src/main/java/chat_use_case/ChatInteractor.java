package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.boundaries.ChatInputBoundary;
import chat_use_case.models.ChatCreationModel;
import chat_use_case.models.ChatRecieveMessagesModel;
import chat_use_case.models.ChatSendMessageModel;

public class ChatInteractor implements ChatInputBoundary {


    ChatDsBoundary DataStore;


    @Override
    public void createChat(ChatCreationModel c) {

    }

    @Override
    public void sendMessage(ChatSendMessageModel m){

    }
}