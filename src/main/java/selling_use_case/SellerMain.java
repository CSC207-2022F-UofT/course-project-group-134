package selling_use_case;


import order_use_case.DoesNotExistException;
import order_use_case.OrderGateway;
import screens.SellerFulfillingOrderScreen;
import screens.SellingScreen;


import java.io.IOException;

public class SellerMain {
    public static void create(String sellerEmail) throws DoesNotExistException {
        OrderGateway gateway;
        try {
            gateway = new OrderGateway("./src/main/java/data_storage/orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        SellingPresenter presenter = new SellingPresenter();
        SellingInputBoundary interactor = new SellingInteractor(presenter, gateway);
        SellingController sellingController = new SellingController(interactor);
        if (gateway.sellerHasOrder(sellerEmail)){
            SellerFulfillingOrderScreen fulfillingOrderScreen = new SellerFulfillingOrderScreen(sellingController, gateway, sellerEmail);
        } else {
            SellingScreen sellingScreen = new SellingScreen(sellingController, gateway, sellerEmail);
        }
    }
}
