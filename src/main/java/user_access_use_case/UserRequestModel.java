package user_access_use_case;

import entities.MealPlan;
import entities.UserType;

public class UserRequestModel {
    private String username;
    private String email;
    private String password;
    private UserType userType;
    private String residence;
    private double balance;

    public UserRequestModel (String name, String email, String password, UserType userType, String residence, double balance){
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
