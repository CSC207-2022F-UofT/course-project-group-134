package selling_use_case;


import order_use_case.DoesNotExistException;
import order_use_case.OrderDsGateway;
import order_use_case.OrderGateway;
import screens.SellerFulfillingOrderScreen;
import screens.SellingScreen;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;


import java.io.IOException;

/**
 * SellingMain is called when an instance of the selling use case is called.
 * Creates and sets up instances of all the relevant classes for the selling use case.
 */
public class SellerMain {
    public static void create(String sellerEmail, String sellerResidence) throws DoesNotExistException {
        OrderDsGateway orderGateway;
        SignUpDsGateway signupGateway;
        try {
            orderGateway = new OrderGateway("./src/main/java/data_storage/orders.csv");
            signupGateway = new SignUpGateway();
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        SellingPresenter presenter = new SellingPresenter();
        SellingInputBoundary interactor = new SellingInteractor(presenter, orderGateway);
        SellingController sellingController = new SellingController(interactor);

        new SellingScreen(sellingController, signupGateway, orderGateway, sellerEmail, sellerResidence);

    }
}
