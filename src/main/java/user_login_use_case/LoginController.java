package user_login_use_case;

import java.io.IOException;

// Use case layer

/**
 * The controller for the login use case. Takes in information from the
 * login screen and sends it to the interactor.
 */
public class LoginController {

    final LoginInputBoundary boundary;

    public LoginController(LoginInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public LoginResponseModel create(String email, String password) throws IOException {
        LoginRequestModel requestModel = new LoginRequestModel(email, password);
        return boundary.create(requestModel);
    }

}
