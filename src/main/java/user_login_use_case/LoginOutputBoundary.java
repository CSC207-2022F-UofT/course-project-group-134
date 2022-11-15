package user_login_use_case;

public interface LoginOutputBoundary {
    public LoginResponseModel prepareSuccessView(LoginResponseModel info);
    public LoginResponseModel prepareFailView(String error);
}
