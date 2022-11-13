package user_login_use_case;

public class LoginFailed extends RuntimeException {
    public LoginFailed(String error){
        super(error);
    }
}
