package user_access_use_case;

import entities.User;
import entities.UserFactory;

import java.io.IOException;

public interface UserDsGateway {
    Boolean existsByEmail(String email);

    void save(UserDsRequestModel requestModel) throws IOException;   // Save new account data

    void save() throws IOException;

    User readUser(String email, UserFactory userFactory);
}
