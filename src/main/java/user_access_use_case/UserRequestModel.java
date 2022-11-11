package user_access_use_case;

public class UserRequestModel {
    private String username;
    private String email;
    private String password;
    private String repeatPassword;

    public UserRequestModel (String name, String email, String password, String repeatPw){
        this.username = name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPw;
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
}
