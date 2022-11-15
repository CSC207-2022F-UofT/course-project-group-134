package user_access_use_case;

public class SignUpPresenter implements SignUpOutputBoundary{
    public SignUpResponseModel prepareSuccessView(SignUpResponseModel info){
        return info;
    }
    public SignUpResponseModel prepareFailView(String error){
        throw new SignUpFailed(error);
    }
}
