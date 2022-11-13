package user_login_use_case;

import entities.User;
import entities.UserFactory;
import user_access_use_case.SignUpDsGateway;

import java.io.IOException;

public class LoginInteractor implements LoginInputBoundary {

    final SignUpDsGateway userDsGateway;
    final LoginPresenter loginPresenter;
    final UserFactory userFactory;

    public LoginInteractor(SignUpDsGateway userDsGateway, LoginPresenter loginPresenter,
                           UserFactory userFactory) {
        this.userDsGateway = userDsGateway;
        this.loginPresenter = loginPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public LoginResponseModel create(LoginRequestModel requestModel) throws IOException {
        if (!userDsGateway.existsByEmail(requestModel.getEmail())) {
            return loginPresenter.prepareFailView("Email does not exist.");
        }
        User user = userDsGateway.readUser(requestModel.getEmail(), userFactory);
        if (!user.getPassword().equals(requestModel.getPassword())) {
            return loginPresenter.prepareFailView("Password is incorrect.");
        }
        LoginResponseModel loginResponseModel = new LoginResponseModel(user);
        return loginResponseModel;
    }
}
