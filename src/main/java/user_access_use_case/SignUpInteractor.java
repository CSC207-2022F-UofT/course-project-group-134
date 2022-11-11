package user_access_use_case;
import entities.User;
import entities.UserFactory;


public class SignUpInteractor implements SignUpInputBoundary {

    final UserDsGateway userDsGateway;
    final UserAccessPresenter userPresenter;
    final UserFactory userFactory;

    if (userDsGateway.existsByEmail(requestModel.getName())) {
        return userPresenter.prepareFailView("User already exists.");
    } else if (!UserRequestModel.getPassword().equals(UserRequestModel.getRepeatPassword())) {
        return userPresenter.prepareFailView("Passwords don't match.");
    }

}
