package user_access_use_case;

import entities.User;
import entities.UserFactory;

import java.io.IOException;

public interface SignUpDsGateway {
    Boolean existsByEmail(String email);

    void save(SignUpDsRequestModel requestModel) throws IOException;   // Save new account data

    void save() throws IOException;

    User readUser(String email, UserFactory userFactory);

    User readUser(String email);

    void subtractPrice(String sellerEmail, double price) throws IOException;

    SignUpDsRequestModel getRequestModelFromEmail(String email);
}
