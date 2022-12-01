package user_login_use_case;


import java.io.IOException;

// Use case layer

/**
 * LoginInputBoundary which purpose is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface LoginInputBoundary {

    LoginResponseModel create(LoginRequestModel requestModel) throws IOException;
}
