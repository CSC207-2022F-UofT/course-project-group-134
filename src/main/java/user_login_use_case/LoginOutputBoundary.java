package user_login_use_case;

public interface LoginOutputBoundary {
    LoginResponseModel prepareSuccessView(LoginResponseModel info);
    LoginResponseModel prepareFailView(String error);
}
