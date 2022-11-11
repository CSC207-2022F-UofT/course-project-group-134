package user_access_use_case;

import java.io.IOException;

public interface SignUpInputBoundary {
    UserResponseModel create(UserRequestModel requestModel) throws IOException;
}
