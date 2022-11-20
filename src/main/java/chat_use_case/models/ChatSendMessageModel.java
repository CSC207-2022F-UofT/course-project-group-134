package chat_use_case.models;

import entities.User;

public class ChatSendMessageModel {

    private final User sender, reciever;
    private final String message;

    public ChatSendMessageModel(User sender, User reciever, String message){
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
    }

    public User getSender(){
        return sender;
    }

    public User getReciever(){
        return reciever;
    }

    public String getMessage(){
        return message;
    }

}
