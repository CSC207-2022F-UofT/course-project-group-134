package user_access_use_case;

import entities.BuyerFactory;
import entities.SellerFactory;
import entities.UserFactory;
import screens.SignupScreen;

import java.io.IOException;

public class SignUpMain {

    public static void create() {
        SignUpDsGateway user;
        try {
            user = new SignUpGateway();
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        SignUpPresenter presenter = new SignUpPresenter();
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);
        SignUpInputBoundary interactor = new SignUpRegisterInteractor(
                user, presenter, userFactory);
        SignUpRequestController signupController = new SignUpRequestController(interactor);
        new SignupScreen(signupController);
    }
}
