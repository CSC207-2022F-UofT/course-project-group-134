package user_access_use_case;

import entities.BuyerFactory;
import entities.SellerFactory;
import entities.UserFactory;
import screens.SignupScreen;
import screens.WelcomeScreen;

import java.io.IOException;

public class SignUpMain {

    public static void create() {
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
        SignupScreen signupScreen = new SignupScreen(signupController);
    }
}
