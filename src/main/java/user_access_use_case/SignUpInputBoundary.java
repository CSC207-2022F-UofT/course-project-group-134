package user_access_use_case;

public interface SignUpInputBoundary {
    UserResponseModel create(UserRequestModel requestModel);
}
