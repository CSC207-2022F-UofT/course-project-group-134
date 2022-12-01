package user_access_use_case;

// Use case layer

/**
 * The SignUpPresenter either returns a success view if the signup information
 * is valid, or a fail view if the information provided is invalid.
 */
public class SignUpPresenter implements SignUpOutputBoundary{
    public SignUpResponseModel prepareSuccessView(SignUpResponseModel info){
        return info;
    }
    public SignUpResponseModel prepareFailView(String error){
        throw new SignUpFailed(error);
    }
}
