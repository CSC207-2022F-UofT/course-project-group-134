package user_access_use_case;

public class SignUpFailed extends RuntimeException{
    public SignUpFailed(String error){
        super(error);
    }
}
