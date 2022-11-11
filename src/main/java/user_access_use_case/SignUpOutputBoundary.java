package user_access_use_case;

public interface SignUpOutputBoundary {
    void prepareSuccessView(String info);
    void prepareFailView(String info);
}