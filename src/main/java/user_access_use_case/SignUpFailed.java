package user_access_use_case;

/**
 * SignUpFailed exception is an exception that gets raised when
 * the user is unsuccessful at signing up.
 */
public class SignUpFailed extends RuntimeException{
    public SignUpFailed(String error){
        super(error);
    }
}
