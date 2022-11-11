package user_access_use_case;

public interface SignUpOutputBoundary {
    public UserResponseModel prepareSuccessView(UserResponseModel info);
    public UserResponseModel prepareFailView(String error);
}
