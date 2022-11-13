package user_access_use_case;
import entities.*;

import java.io.IOException;
import java.util.ArrayList;


public class UserRegisterInteractor implements SignUpInputBoundary {

    final UserDsGateway userDsGateway;
    final UserAccessPresenter userPresenter;
    final UserFactory userFactory;

    public UserRegisterInteractor(UserDsGateway userDsGateway, UserAccessPresenter userPresenter,
                                  UserFactory userFactory) {
        this.userDsGateway = userDsGateway;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public UserResponseModel create(UserRequestModel requestModel) throws IOException {
        if (userDsGateway.existsByEmail(requestModel.getEmail())) {
            return userPresenter.prepareFailView("User already exists based on email.");
        }
        if (!requestModel.getUsername().matches("[A-Za-z0-9]+")) {
            return userPresenter.prepareFailView("Username is not alphanumeric.");
        }
        if (!requestModel.getEmail().matches("[A-Za-z0-9]+")) {
            return userPresenter.prepareFailView("Email is not alphanumeric.");
        }
        if (!requestModel.getPassword().matches("[A-Za-z0-9]+")) {
            return userPresenter.prepareFailView("Password is not alphanumeric.");
        }

        UserDsRequestModel userDsModel;

        if (requestModel.getUserType() == UserType.BUYER) {
            userDsModel = new UserDsRequestModel(requestModel.getUsername(), requestModel.getPassword(),
                    requestModel.getEmail(), "Buyer", 0, "none");
        } else {
            userDsModel = new UserDsRequestModel(requestModel.getUsername(), requestModel.getPassword(),
                    requestModel.getEmail(), "Seller",
                    requestModel.getBalance(), requestModel.getResidence());
        }


        userDsGateway.save(userDsModel);

        UserResponseModel accountResponseModel = new UserResponseModel(requestModel.getUsername());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
