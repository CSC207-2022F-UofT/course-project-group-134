package chat_use_case.models;

import entities.User;

public class ChatCreationRequestModel {

    private final User sender, receiver;


    public ChatCreationRequestModel(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
    }

    public User[] getUsers(){
        return new User[]{sender, receiver};
    }

    public User getSender(){
        return sender;
    }

    public User getReceiver(){
        return receiver;
    }




}
