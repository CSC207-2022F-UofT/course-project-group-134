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

    public void transferRole(){

    }

    public void report(String username){

    }
}
