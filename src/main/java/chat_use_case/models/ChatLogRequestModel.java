package chat_use_case.models;

import entities.User;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;

import java.io.IOException;

/**
 * Request model for getting the chat log.
 */
public class ChatLogRequestModel {

    private final User user1, user2;

    /**
     * Constructor for this class
     * @param user1 One of the users for getting the message history from.
     * @param user2 The other user that we are getting a message history for.
     */
    public ChatLogRequestModel(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Another, overloaded constructor for this class.
     * @param email1 The email of one user
     * @param email2 The email of the other user
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    public ChatLogRequestModel(String email1, String email2) throws IOException {
         SignUpDsGateway userGateway = new SignUpGateway();
         User user1 = userGateway.readUser(email1);
         User user2 = userGateway.readUser(email2);


         this.user1 = user1;
         this.user2 = user2;
    }

    /**
     * Getter function for the users of this chat request model
     * @return Returns an array containing the users.
     */
    public User[] getUsers(){
        return new User[]{user1, user2};
    }


}
