package user_login_use_case;

import entities.User;

public class LoginResponseModel {

    private final User user;

    public LoginResponseModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
