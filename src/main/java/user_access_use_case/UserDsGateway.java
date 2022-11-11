package user_access_use_case;

import java.io.IOException;

public interface UserDsGateway {
    Boolean existsByEmail(String email);

    void save(UserRequestModel requestModel) throws IOException;   // Save new account data

    void save() throws IOException;
}
