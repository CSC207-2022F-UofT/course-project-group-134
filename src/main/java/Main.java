import screens.LoginScreen;
import screens.WelcomeScreen;
import user_access_use_case.SignUpInputBoundary;
import user_access_use_case.UserRequestController;

public class Main {

    public static void main(String[] args) {
        SignUpInputBoundary interactor = null; // TODO make not null.
        UserRequestController signupController = new UserRequestController(interactor);
        WelcomeScreen screen = new WelcomeScreen(signupController);
    }
}
