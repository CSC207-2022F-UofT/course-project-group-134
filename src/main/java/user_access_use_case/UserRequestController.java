package user_access_use_case;

import entities.MealPlan;
import entities.UserType;

import java.io.IOException;

public class UserRequestController {
    SignUpInputBoundary boundary;

    public UserRequestController(SignUpInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public UserResponseModel create(String username, String email, String password, String userTypeString,
                                    String residence, String balanceString) throws IOException {
        UserRequestModel requestModel;
        UserType userType;
        double balance = 0;
        if (userTypeString.equals("Seller")) {
            userType = UserType.SELLER;
            balance = Double.parseDouble(balanceString);
        } else {
            userType = UserType.BUYER;
            residence = "none";
        }
        requestModel = new UserRequestModel(username, email, password, userType, residence, balance);
        return boundary.create(requestModel);
    }
}
