package chat_use_case.models;

import entities.User;

/**
 * Request model for creating a chat
 */
public class ChatCreationRequestModel {

    private final User sender, receiver;

    /**
     * Constructor for this class
     * @param sender The sender of the chat
     * @param receiver The receiver of the chat
     */
    public ChatCreationRequestModel(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Getter functions for the two users the chat is between
     * @return The two users the chat is between
     */
    public User[] getUsers(){
        return new User[]{sender, receiver};
    }

    /**
     * The getter function for the sender of the chat
     * @return the sender of the chat
     */
    public User getSender(){
        return sender;
    }

    /**
     * The getter function for the receiver of the chat
     * @return The receiver of the chat
     */
    public User getReceiver(){
        return receiver;
    }




}
