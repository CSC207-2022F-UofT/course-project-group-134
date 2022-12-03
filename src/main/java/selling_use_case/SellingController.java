package selling_use_case;

import java.io.IOException;


/**
 * The controller for the selling use case. Takes in information from the
 * selling screen and sends it to the interactor.
 */
public class SellingController {
    final SellingInputBoundary boundary;

    public SellingController(SellingInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public void accept(String sellerEmail, String orderNumberString) throws IOException {
        SellingRequestModel requestModel = new SellingRequestModel(sellerEmail,
                Integer.parseInt(orderNumberString));
        boundary.accept(requestModel);
    }
}
