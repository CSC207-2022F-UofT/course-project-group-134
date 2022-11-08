package entities;

import java.util.ArrayList;

public class Chat {
    private User user1, user2;
    private ArrayList<String> chatLog;

    public Chat(User user1, User user2) {
        this.chatLog = new ArrayList<>();
        this.user1 = user1;
        this.user2 = user2;
    }

    public ArrayList<String> getChatLog() {
        return chatLog;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    // TODO a lot of other stuff...
}
