package user_login_use_case;

import entities.BuyerFactory;
import entities.SellerFactory;
import entities.UserFactory;
import screens.LoginScreen;
import user_access_use_case.*;

import java.io.IOException;

/**
 * LoginMain is called when an instance of the login use case is called.
 * Creates and sets up instances of all the relevant classes for the login use case.
 */
public class LoginMain {

    public static void create() {
        SignUpDsGateway user;
        try {
            user = new SignUpGateway();
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        LoginPresenter presenter = new LoginPresenter();
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);
        LoginInputBoundary interactor = new LoginInteractor(user, presenter, userFactory);
        LoginController loginController = new LoginController(interactor);
        new LoginScreen(loginController);
    }
}
