package chat_use_case.models;

import entities.ChatMessage;

import java.util.ArrayList;

public class ChatDataRecieveModel {


    private final ArrayList<ChatMessage> chatMessages;

    private final boolean chat_exists;
    public ChatDataRecieveModel(ArrayList<ChatMessage> messages, boolean chat_exists){
        this.chatMessages = messages; this.chat_exists = chat_exists;
    }


    public ArrayList<ChatMessage> getChatMessages(){
        return chatMessages;
    }

    public boolean doesChatExist(){
        return chat_exists;
    }

}
