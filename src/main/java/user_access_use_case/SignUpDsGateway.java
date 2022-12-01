package user_access_use_case;

import entities.User;
import entities.UserFactory;

import java.io.IOException;

// Use case layer

/**
 * SignUpDsGateway which purpose is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface SignUpDsGateway {
    Boolean existsByEmail(String email);

    void save(SignUpDsRequestModel requestModel) throws IOException;

    void save() throws IOException;

    User readUser(String email, UserFactory userFactory);

    User readUser(String email);

    void subtractPrice(String sellerEmail, double price) throws IOException;

    SignUpDsRequestModel getRequestModelFromEmail(String email);
}
