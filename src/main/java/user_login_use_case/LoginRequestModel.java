package user_login_use_case;

// Use case layer

/**
 * LoginRequestModel has the credential data which the user inputs
 * into the login screen.
 */
public class LoginRequestModel {

    private final String email;
    private final String password;

    public LoginRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
