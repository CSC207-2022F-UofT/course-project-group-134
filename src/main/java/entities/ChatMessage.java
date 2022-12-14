package entities;

public class ChatMessage {

    private User sender;
    private String message;

    public ChatMessage(User sender, String message){
        this.sender = sender;
        this.message = message;
    }

    public User getSender(){
        return sender;
    }

    public String getContents(){
        return message;
    }

}
