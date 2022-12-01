package user_login_use_case;

/**
 * LoginFailed exception is an exception that gets raised when
 * the user is unsuccessful at logging in.
 */
public class LoginFailed extends RuntimeException {
    public LoginFailed(String error){
        super(error);
    }
}
