package entities;

import java.util.ArrayList;

public abstract class User {
    private String username;
    private String password;
    private int numReported;
    private String[] chats;
    private String email;

    public User (String name, String password, String email){
        this.username = name;
        this.password = password;
        this.email = email;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUsername(String newName){
        this.username = newName;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void transferRole(){
        //TODO figure out later
    }

    public void report(User user){
        user.numReported += 1;
    }
}
