package user_login_use_case;

public class LoginPresenter implements LoginOutputBoundary {
    public LoginResponseModel prepareSuccessView(LoginResponseModel info){
        return info;
    }
    public LoginResponseModel prepareFailView(String error){
        throw new LoginFailed(error);
    }
}
