package user_login_use_case;

// Use case layer

/**
 * The LoginPresenter either returns a success view if the credentials provided
 * are valid, or a fail view if the credentials provided are invalid.
 */
public class LoginPresenter implements LoginOutputBoundary {
    public LoginResponseModel prepareSuccessView(LoginResponseModel info){
        return info;
    }
    public LoginResponseModel prepareFailView(String error){
        throw new LoginFailed(error);
    }
}
