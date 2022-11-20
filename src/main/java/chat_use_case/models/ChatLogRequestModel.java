package chat_use_case.models;

import entities.User;

public class ChatLogRequestModel {

    private final User user1, user2;

    public ChatLogRequestModel(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }

    public User[] getUsers(){
        return new User[]{user1, user2};
    }


}
