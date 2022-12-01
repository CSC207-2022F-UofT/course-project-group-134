package chat_use_case.models;

import entities.User;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;

import java.io.IOException;

public class ChatSendMessageModel {

    private final User sender, reciever;
    private final String message;

    public ChatSendMessageModel(User sender, User reciever, String message){
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
    }

    public ChatSendMessageModel(String userEmail, String recieverEmail, String message){
        SignUpDsGateway userGateway = null;
        try {
            userGateway = new SignUpGateway();
        } catch (IOException e) {
            e.printStackTrace();
        }
        User sender = userGateway.readUser(userEmail);
        User reciever = userGateway.readUser(recieverEmail);
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
