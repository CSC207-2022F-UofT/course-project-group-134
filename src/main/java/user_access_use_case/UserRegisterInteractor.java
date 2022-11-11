package user_access_use_case;
import entities.*;


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
    public UserResponseModel create(UserRequestModel requestModel) {
        if (userDsGateway.existsByEmail(requestModel.getEmail())) {
            return userPresenter.prepareFailView("User already exists based on email.");
        }
        User user;

        if (requestModel.getUserType() == UserType.BUYER) {
            user = userFactory.createBuyer(requestModel.getUserType(), requestModel.getUsername(),
                    requestModel.getPassword(), requestModel.getEmail());
        }
        else {
            user = userFactory.createSeller(requestModel.getUserType(), requestModel.getUsername(),
                    requestModel.getPassword(), requestModel.getMealPlan(), requestModel.getEmail());;
        }

        UserDsRequestModel userDsModel = new UserDsRequestModel(user.getUsername(), user.getPassword(), user.getEmail());
        userDsGateway.save(userDsModel);

        UserResponseModel accountResponseModel = new UserResponseModel(user.getUsername());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
