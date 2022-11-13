import entities.*;
import screens.LoginScreen;
import screens.WelcomeScreen;
import user_access_use_case.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        UserDsGateway user;
        try {
            user = new UserGateway("./users.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        UserAccessPresenter presenter = new UserAccessPresenter();
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);
        SignUpInputBoundary interactor = new UserRegisterInteractor(
                user, presenter, userFactory);
        UserRequestController signupController = new UserRequestController(interactor);
        WelcomeScreen welcomeScreen = new WelcomeScreen(signupController);
    }
}
