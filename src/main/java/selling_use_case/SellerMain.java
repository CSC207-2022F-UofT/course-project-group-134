package selling_use_case;


import order_use_case.DoesNotExistException;
import order_use_case.OrderGateway;
import screens.SellerFulfillingOrderScreen;
import screens.SellingScreen;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;


import java.io.IOException;

public class SellerMain {
    public static void create(String sellerEmail, String sellerResidence) throws DoesNotExistException {
        OrderGateway orderGateway;
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
        if (orderGateway.sellerHasOrder(sellerEmail)){
            SellerFulfillingOrderScreen fulfillingOrderScreen = new SellerFulfillingOrderScreen(
                    signupGateway, orderGateway, sellerEmail,
                    orderGateway.getPriceFromOrderNumber(orderGateway.getOrderNumberFromSellerEmail(sellerEmail)));
        } else {
            SellingScreen sellingScreen = new SellingScreen(sellingController, signupGateway, orderGateway, sellerEmail, sellerResidence);
        }
    }
}
