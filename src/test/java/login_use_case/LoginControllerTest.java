package login_use_case;

import entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sign_up_use_case.SignUpControllerTest;
import user_access_use_case.*;
import user_login_use_case.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    private LoginInputBoundary loginInteractor;
    private LoginController loginController;
    private SignUpDsGateway gateway;

    @BeforeEach
    public void setUp() throws Exception {
        try {
            gateway = new SignUpGateway();
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        LoginPresenter loginPresenter = new LoginPresenter();
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);
        loginInteractor = new LoginInteractor(gateway, loginPresenter, userFactory);
        loginController = new LoginController(loginInteractor);

        SignUpPresenter signupPresenter = new SignUpPresenter();
        SignUpInputBoundary signupInteractor = new SignUpRegisterInteractor(
                gateway, signupPresenter, userFactory);
        SignUpRequestController signupController = new SignUpRequestController(signupInteractor);
        try {
            signupController.create("lookcook",
                    "aditya.bandekar@mail.utoronto.ca", "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            signupController.create("cooklook",
                    "cooklook@mail.utoronto.ca", "password2", UserType.BUYER.toString(),
                    ResidenceType.CHESTNUT.toString(), "0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            fail("Threw sign up error");
        }
    }

    @AfterEach
    void tearDown() {
        new File("./src/test/resources/users.csv").delete();
    }

    @Test
    void testLoginEmailDoesNotExist() throws IOException {
        try {
            loginController.create("badEmail@mail.utoronto.ca", "1234");
            fail("LoginFailed should have been called");
        } catch (LoginFailed ex) {
            // The exception should be called.
        }
    }

    @Test
    void testLoginPasswordIsIncorrect() throws IOException {
        try {
            loginController.create("aditya.bandekar@mail.utoronto.ca", "54321");
            fail("LoginFailed should have been called");
        } catch (LoginFailed ex) {
            // The exception should be called.
        }
    }


    @Test
    void testLoginSuccessful() throws IOException {
        try {
            LoginResponseModel responseModel = loginController.create("aditya.bandekar@mail.utoronto.ca", "1234");
            User user = responseModel.getUser();
            assert(user instanceof Seller);
            Seller seller = (Seller) user;
            assertEquals(seller.getEmail(), "aditya.bandekar@mail.utoronto.ca");
            assertEquals(seller.getUsername(), "lookcook");
            assertEquals(seller.getPassword(), "1234");
            assertEquals(seller.getMealPlan().getBalance(), 10.0);
            assertEquals(seller.getMealPlan().getResidence(), ResidenceType.ST_MICHAELS_COLLEGE.toString());
        } catch (LoginFailed ex) {
            fail("LoginFailed should not have been called");
        }
    }
}
