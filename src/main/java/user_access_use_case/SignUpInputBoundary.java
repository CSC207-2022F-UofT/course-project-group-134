package user_access_use_case;

import java.io.IOException;

public interface SignUpInputBoundary {
    SignUpResponseModel create(SignUpRequestModel requestModel) throws IOException;
}
