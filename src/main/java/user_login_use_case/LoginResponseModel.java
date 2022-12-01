package user_login_use_case;

import entities.User;

// Use case layer

/**
 * LoginResponseModel contains the user object which corresponds to the
 * credentials provided when the login is successful.
 */
public class LoginResponseModel {

    private final User user;

    public LoginResponseModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
