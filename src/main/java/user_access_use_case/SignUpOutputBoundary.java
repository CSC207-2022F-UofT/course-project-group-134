package user_access_use_case;

// Use case layer

/**
 * SignUpOutputBoundary which purpose is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface SignUpOutputBoundary {
    SignUpResponseModel prepareSuccessView(SignUpResponseModel info);
    SignUpResponseModel prepareFailView(String error);
}
