package chat_use_case.models;

import entities.ChatMessage;

import java.util.ArrayList;

public class ChatLogRecieveModel {


    private final ArrayList<ChatMessage> chatMessages;

    public ChatLogRecieveModel(ArrayList<ChatMessage> messages){
        this.chatMessages = messages;
    }


}
