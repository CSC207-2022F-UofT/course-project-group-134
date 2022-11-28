package chat_use_case.models;

import entities.User;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;

import java.io.IOException;

public class ChatLogRequestModel {

    private final User user1, user2;

    public ChatLogRequestModel(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }

    public ChatLogRequestModel(String email1, String email2) throws IOException {
         SignUpDsGateway userGateway = new SignUpGateway();
         User user1 = userGateway.readUser(email1);
         User user2 = userGateway.readUser(email2);


         this.user1 = user1;
         this.user2 = user2;
    }

    public User[] getUsers(){
        return new User[]{user1, user2};
    }


}
