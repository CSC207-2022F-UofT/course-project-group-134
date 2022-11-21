package sign_up_use_case;

import entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import user_access_use_case.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SignUpGatewayTest {

    private SignUpInputBoundary interactor;
    private SignUpDsGateway gateway;
    private SignUpRequestController controller;
    private UserFactory userFactory;

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
        userFactory = new UserFactory(buyerFactory, sellerFactory);
        interactor = new SignUpRegisterInteractor(
                gateway, presenter, userFactory);
        controller = new SignUpRequestController(interactor);

        try {
            controller.create("lookcook",
                    "aditya.bandekar@mail.utoronto.ca", "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            controller.create("cooklook",
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
    void testIfEmailExists() {
        assert(gateway.existsByEmail("aditya.bandekar@mail.utoronto.ca"));
        assert(gateway.existsByEmail("cooklook@mail.utoronto.ca"));
    }

    @Test
    void testIfEmailDoesNotExists() {
        assert(!gateway.existsByEmail("another.email@mail.utoronto.ca"));
    }


    @Test
    void testRequestModelFromEmail() {
        SignUpDsRequestModel requestModel = gateway.getRequestModelFromEmail("aditya.bandekar@mail.utoronto.ca");
        assertEquals("aditya.bandekar@mail.utoronto.ca", requestModel.getEmail());
        assertEquals("lookcook", requestModel.getUsername());
        assertEquals("1234", requestModel.getPassword());
        assertEquals(UserType.SELLER.toString(), requestModel.getUserType());
        assertEquals(ResidenceType.ST_MICHAELS_COLLEGE.toString(), requestModel.getResidence());
        assertEquals(10.0, requestModel.getMealPlanBalance());
    }

    @Test
    void testSubtractPrice() throws IOException {
        gateway.subtractPrice("aditya.bandekar@mail.utoronto.ca", 5.0);
        SignUpDsRequestModel requestModel = gateway.getRequestModelFromEmail("aditya.bandekar@mail.utoronto.ca");
        assertEquals(5.0, requestModel.getMealPlanBalance());
    }

    @Test
    void testReadUser() {
        User user = gateway.readUser("aditya.bandekar@mail.utoronto.ca", userFactory);
        assert(user instanceof Seller);
        Seller seller = (Seller) user;
        assertEquals(seller.getEmail(), "aditya.bandekar@mail.utoronto.ca");
        assertEquals(seller.getUsername(), "lookcook");
        assertEquals(seller.getPassword(), "1234");
        assertEquals(seller.getMealPlan().getBalance(), 10.0);
        assertEquals(seller.getMealPlan().getResidence(), ResidenceType.ST_MICHAELS_COLLEGE.toString());
    }
}
