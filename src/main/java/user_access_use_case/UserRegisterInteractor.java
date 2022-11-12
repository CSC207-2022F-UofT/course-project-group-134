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

        User user;
        UserDsRequestModel userDsModel;

        if (requestModel.getUserType() == UserType.BUYER) {
            user = userFactory.createBuyer(requestModel.getUserType(), requestModel.getUsername(),
                    requestModel.getPassword(), requestModel.getEmail());
            ArrayList<String> diningHalls = new ArrayList<>();
            diningHalls.add("none"); // TODO, this person is a buyer. What to do with its dining halls list.
            userDsModel = new UserDsRequestModel(user.getUsername(), user.getPassword(), user.getEmail(), "Buyer",
                        0, diningHalls);
        }
        else {
            user = userFactory.createSeller(requestModel.getUserType(), requestModel.getUsername(),
                    requestModel.getPassword(), requestModel.getMealPlan(), requestModel.getEmail());;

            double mealPlanBalance = requestModel.getMealPlan().getBalance();
            ArrayList<DiningHall> diningHallsList = requestModel.getMealPlan().getAssociatedDiningHalls();
            ArrayList<String> diningHallsNames = new ArrayList<>();

            for(DiningHall hall: diningHallsList){
                diningHallsNames.add(hall.getName());
            }
            userDsModel = new UserDsRequestModel(user.getUsername(), user.getPassword(), user.getEmail(),
                    "Seller", mealPlanBalance, diningHallsNames);
        }


        userDsGateway.save(userDsModel);

        UserResponseModel accountResponseModel = new UserResponseModel(user.getUsername());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
