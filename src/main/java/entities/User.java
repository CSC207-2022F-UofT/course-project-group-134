package entities;

import java.util.ArrayList;

public abstract class User {
    private String username;
    private String password;
    private int numReported;
    private String[] chats;

    public User (String name, String password){
        this.username = name;
        this.password = password;
    }

    public void setUsername(String newName){
        this.username = newName;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public void transferRole(){
        //TODO figure out later
    }

    public void report(User user){
        user.numReported += 1;
    }
}
