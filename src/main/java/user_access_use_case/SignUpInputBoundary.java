package user_access_use_case;

import java.io.IOException;

// Use case layer

/**
 * SignUpInputBoundary which purpose is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface SignUpInputBoundary {
    SignUpResponseModel create(SignUpRequestModel requestModel) throws IOException;
}
