package user_access_use_case;

import entities.UserType;

// Use case layer

/**
 * SignUpRequestModel has the signup information which the user inputs
 * into the signup screen.
 */
public class SignUpRequestModel {
    private final String username;
    private final String email;
    private final String password;
    private final UserType userType;
    private final String residence;
    private final double balance;

    public SignUpRequestModel(String name, String email, String password, UserType userType, String residence, double balance){
        this.username = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.residence = residence;
        this.balance = balance;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getResidence() {
        return residence;
    }

    public double getBalance() {
        return balance;
    }
}
