package user_access_use_case;

import entities.MealPlan;
import entities.UserType;

import java.io.IOException;

public class UserRequestController {
    SignUpInputBoundary boundary;

    public UserRequestController(SignUpInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public UserResponseModel create(String username, String email, String password, UserType userType, MealPlan mealPlan) throws IOException {
        UserRequestModel requestModel;
        if (userType == UserType.SELLER) {
            requestModel = new UserRequestModel(username, email, password, userType, mealPlan);
        }
        else {
            requestModel = new UserRequestModel(username, email, password, userType);
        }
        return boundary.create(requestModel);
    }
}
