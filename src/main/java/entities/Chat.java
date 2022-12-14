package entities;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private final List<ChatMessage> chatLog;
    private User user1, user2;
    public Chat(User user1, User user2){
        this.chatLog = new ArrayList<>();
        this.user1 = user1;
        this.user2 = user2;
    }

    public Chat(User user1, User user2, ArrayList<ChatMessage> chatlog){
        this.chatLog = chatlog;
        this.user1 = user1;
        this.user2 = user2;
    }

    public List<ChatMessage> getChatLog() {
        return chatLog;
    }
    public void sendMessage(ChatMessage message){
        chatLog.add(message);
    }


    public User[] getUsers(){
        return new User[]{user1, user2};
    }


}
