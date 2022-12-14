package user_access_use_case;
import entities.*;

import java.io.IOException;

// Use case layer

/**
 * The interactor for the signup use case. Performs the logic for the signup use case.
 */
public class SignUpRegisterInteractor implements SignUpInputBoundary {

    final SignUpDsGateway userDsGateway;
    final SignUpPresenter userPresenter;
    final UserFactory userFactory;

    public SignUpRegisterInteractor(SignUpDsGateway userDsGateway, SignUpPresenter userPresenter,
                                    UserFactory userFactory) {
        this.userDsGateway = userDsGateway;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public SignUpResponseModel create(SignUpRequestModel requestModel) throws IOException {
        if (userDsGateway.existsByEmail(requestModel.getEmail())) {
            return userPresenter.prepareFailView("User already exists based on email.");
        }
        if (userDsGateway.existsByUsername(requestModel.getUsername())) {
            return userPresenter.prepareFailView("That username is already used.");
        }
        if (!requestModel.getUsername().matches("[A-Za-z0-9]+")) {
            return userPresenter.prepareFailView("Username is not alphanumeric.");
        }
        // note to self: after a-zA-Z0-9 is special character escape, before $ is end anchor
        if (!requestModel.getEmail().matches("^[a-zA-Z0-9!@#$&()\\\\-`.]*@mail.utoronto.ca$")) {
            return userPresenter.prepareFailView("Email is not U of T email.");
        }
        if (!requestModel.getPassword().matches("[A-Za-z0-9]+")) {
            return userPresenter.prepareFailView("Password is not alphanumeric.");
        }

        SignUpDsRequestModel userDsModel;

        if (requestModel.getUserType() == UserType.BUYER) {
            userDsModel = new SignUpDsRequestModel(requestModel.getUsername(), requestModel.getPassword(),
                    requestModel.getEmail(), UserType.BUYER.toString(), 0, "none");
        } else {
            userDsModel = new SignUpDsRequestModel(requestModel.getUsername(), requestModel.getPassword(),
                    requestModel.getEmail(), UserType.SELLER.toString(),
                    requestModel.getBalance(), requestModel.getResidence());
        }


        userDsGateway.save(userDsModel);

        SignUpResponseModel accountResponseModel = new SignUpResponseModel(requestModel.getUsername());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
