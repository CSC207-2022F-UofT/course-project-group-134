package user_access_use_case;

public interface SignUpOutputBoundary {
    public SignUpResponseModel prepareSuccessView(SignUpResponseModel info);
    public SignUpResponseModel prepareFailView(String error);
}
