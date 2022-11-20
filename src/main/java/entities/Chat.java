package entities;

import java.util.ArrayList;

public class Chat {

    private final ArrayList<ChatMessage> chatLog;
    private final User user1, user2;
    public Chat() {
        this.chatLog = new ArrayList<>();
    }

    public Chat(User user1, User user2){
        this.chatLog = new ArrayList<>();
        this.user1 = user1;
        this.user2 = user2;
    }

    public ArrayList<ChatMessage> getChatLog() {
        return chatLog;
    }
    public void sendMessage(ChatMessage message){
        chatLog.add(message);
    }


    public User[] getUsers(){
        return new User[]{user1, user2};
    }


}
