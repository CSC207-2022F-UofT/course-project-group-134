package user_login_use_case;

// Use case layer

/**
 * LoginOutputBoundary which purpose is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface LoginOutputBoundary {
    LoginResponseModel prepareSuccessView(LoginResponseModel info);
    LoginResponseModel prepareFailView(String error);
}
