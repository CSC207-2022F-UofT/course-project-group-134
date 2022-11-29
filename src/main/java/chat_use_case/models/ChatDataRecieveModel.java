package chat_use_case.models;

import entities.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatDataRecieveModel {


    private final List<ChatMessage> chatMessages;

    private final boolean chat_exists;
    public ChatDataRecieveModel(List<ChatMessage> messages, boolean chat_exists){
        this.chatMessages = messages; this.chat_exists = chat_exists;
    }


    public List<ChatMessage> getChatMessages(){
        return chatMessages;
    }

    public boolean doesChatExist(){
        return chat_exists;
    }

}
