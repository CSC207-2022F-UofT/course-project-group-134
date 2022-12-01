package user_login_use_case;

import entities.User;
import entities.UserFactory;
import user_access_use_case.SignUpDsGateway;

import java.io.IOException;

// Use case layer

/**
 * The interactor for the login use case. Performs the logic for the login use case.
 */
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

    /**
     *  Queries the gateway if the user credentials provided are valid.
     *  If the login is successful, returns the LoginResponseModel. Otherwise,
     *  the interactor calls and prepares and returns a fail view.
     * @param requestModel the request model representing the credentials inputted.
     * @return LoginResponseModel with the user information if the credentials are valid.
     */
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
