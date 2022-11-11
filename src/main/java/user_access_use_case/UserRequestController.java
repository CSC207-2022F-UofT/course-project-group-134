package user_access_use_case;

import entities.UserType;

public class UserRequestController {
    SignUpInputBoundary boundary;

    public UserRequestController(SignUpInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public UserResponseModel create(String username, String email, String password, UserType userType) {
        UserRequestModel requestModel = new UserRequestModel(username, email, password, userType);

        return boundary.create(requestModel);
    }
}
