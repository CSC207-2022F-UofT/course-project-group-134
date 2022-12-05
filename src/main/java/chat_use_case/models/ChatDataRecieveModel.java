package chat_use_case.models;

import entities.ChatMessage;

import java.util.List;

/**
 * The data receive model for the Chat use case.
 */
public class ChatDataRecieveModel {


    private final List<ChatMessage> chatMessages;
    /**
     * Whether the chat exists.
     */
    private final boolean chat_exists;

    /**
     * Constructor for this class.
     * @param messages A list of chat messages
     * @param chat_exists Whether the chat exists
     */
    public ChatDataRecieveModel(List<ChatMessage> messages, boolean chat_exists){
        this.chatMessages = messages; this.chat_exists = chat_exists;
    }

    /**
     * Getter function for the chat messages
     * @return returns the chat messages
     */
    public List<ChatMessage> getChatMessages(){
        return chatMessages;
    }

    /**
     * Getter function for whether the chat exists
     * @return whether the chat exists
     */
    public boolean doesChatExist(){
        return chat_exists;
    }

}
