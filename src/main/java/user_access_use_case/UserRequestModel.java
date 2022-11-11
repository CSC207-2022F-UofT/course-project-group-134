package user_access_use_case;

import entities.UserType;

public class UserRequestModel {
    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private UserType userType;

    public UserRequestModel (String name, String email, String password, String repeatPw, UserType userType){
        this.username = name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPw;
        this.userType = userType;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRepeatPassword(){
        return this.repeatPassword;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }
}
