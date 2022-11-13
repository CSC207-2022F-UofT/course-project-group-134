package user_login_use_case;

import user_access_use_case.SignUpInputBoundary;
import user_access_use_case.UserRequestModel;

import java.io.IOException;

public class LoginController {

    LoginInputBoundary boundary;

    public LoginController(LoginInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public LoginResponseModel create(String email, String password) throws IOException {
        LoginRequestModel requestModel = new LoginRequestModel(email, password);
        return boundary.create(requestModel);
    }

}
