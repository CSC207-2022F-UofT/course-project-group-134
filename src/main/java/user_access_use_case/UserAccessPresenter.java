package user_access_use_case;

public class UserAccessPresenter implements SignUpOutputBoundary{
    public UserResponseModel prepareSuccessView(UserResponseModel info){
        return info;
    }
    public UserResponseModel prepareFailView(String error){
        throw new SignUpFailed(error);
    }
}
