package user_login_use_case;


import java.io.IOException;

public interface LoginInputBoundary {
    LoginResponseModel create(LoginRequestModel requestModel) throws IOException;
}
