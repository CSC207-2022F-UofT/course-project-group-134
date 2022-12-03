package sign_up_use_case;

import entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user_access_use_case.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpControllerTest {


    private SignUpInputBoundary interactor;
    private SignUpDsGateway gateway;
    private SignUpRequestController controller;

    @BeforeEach
    void setUp() throws Exception {
        try {
            gateway = new SignUpGateway("./src/test/resources/users.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        SignUpPresenter presenter = new SignUpPresenter();
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);
        interactor = new SignUpRegisterInteractor(
                gateway, presenter, userFactory);
        controller = new SignUpRequestController(interactor);
    }

    @AfterEach
    void tearDown() {
        new File("./src/test/resources/users.csv").delete();
    }

    @Test
    void testSignUpWithoutUTorontoEmail(){
        try {
            controller.create("lookcook", "badEmail",
                    "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            fail("Didn't throw signup error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            // threw sign up exception
        }
    }

    @Test
    void testSignUpBadUsername(){
        try {
            controller.create("look  cook", "aditya.bandekar@mail.utoronto.ca",
                    "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            fail("Didn't throw signup error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            // threw sign up exception
        }
    }

    @Test
    void testSignUpBadPassword(){
        try {
            controller.create("lookcook", "aditya.bandekar@mail.utoronto.ca",
                    "12 34", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            fail("Didn't throw signup error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            // threw sign up exception
        }
    }

    @Test
    void testSignUpSuccessful(){
        try {
            SignUpResponseModel responseModel = controller.create("lookcook",
                    "aditya.bandekar@mail.utoronto.ca", "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            assertEquals(responseModel.getName(), "lookcook");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            fail("Threw sign up error");
        }
    }

    @Test
    void testDuplicateEmail(){
        try {
            controller.create("lookcook",
                    "aditya.bandekar@mail.utoronto.ca", "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            controller.create("username2",
                    "aditya.bandekar@mail.utoronto.ca", "password2", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            fail("Didn't throw signup error.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            // threw sign up error
        }
    }

    @Test
    void testDuplicateUsername(){
        try {
            controller.create("lookcook",
                    "aditya.bandekar@mail.utoronto.ca", "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            controller.create("lookcook",
                    "aditya.bandekar2@mail.utoronto.ca", "password2", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            fail("Didn't throw signup error.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            // threw sign up error
        }
    }

}
