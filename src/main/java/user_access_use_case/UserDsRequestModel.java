package user_access_use_case;

public class UserDsRequestModel {
    private String username;
    private String password;
    private String email;

    public UserDsRequestModel(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {return this.username;}
    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
