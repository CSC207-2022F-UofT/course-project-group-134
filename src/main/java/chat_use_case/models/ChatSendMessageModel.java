package chat_use_case.models;

import entities.User;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;

import java.io.IOException;

/**
 * The message send model. This represents a message being sent.
 */
public class ChatSendMessageModel {

    private final User sender, reciever;
    /**
     * Text body of the message being sent
     */
    private final String message;

    public ChatSendMessageModel(User sender, User reciever, String message){
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
    }

    /**
     * Constructor for this class.
     * @param userEmail The email of the sender.
     * @param recieverEmail The email of the receiver.
     * @param message The text body of the message.
     */
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
        System.out.println(sender);
        System.out.println(reciever);
    }

    /**
     * Getter function for the sender
     * @return The sender of the message
     */
    public User getSender(){
        return sender;
    }

    /**
     * Getter function for the receiver of this message
     * @return The receiver of this message
     */
    public User getReciever(){
        return reciever;
    }

    /**
     * Getter function for the message body of this message.
     * @return The text body of the message being sent.
     */
    public String getMessage(){
        return message;
    }

}
