package user_access_use_case;

public interface SignUpOutputBoundary {
    SignUpResponseModel prepareSuccessView(SignUpResponseModel info);
    SignUpResponseModel prepareFailView(String error);
}
