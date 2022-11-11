package user_access_use_case;

public class UserRequestController {
    SignUpInputBoundary boundary;

    public UserRequestController(SignUpInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    UserResponseModel create(String username, String email, String password, String repeat) {
        UserRequestModel requestModel = new UserRequestModel(username, email, password, repeat);

        return boundary.create(requestModel);
    }
}
