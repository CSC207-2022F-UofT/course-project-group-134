package selling_use_case;


import screens.SellingScreen;


import java.io.IOException;

public class SellerMain {
    public static void create() {
        SellingDsGateway gateway;
        try {
            gateway = new SellingGateway("./orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        SellingPresenter presenter = new SellingPresenter();
        SellingInputBoundary interactor = new SellingInteractor(presenter, gateway);
        SellingController sellingController = new SellingController(interactor);
        SellingScreen sellingScreen = new SellingScreen(sellingController);
    }
}
