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

    public SellingResponseModel accept(String sellerEmail, String orderNumberString, String sellerName) throws IOException {
        SellingRequestModel requestModel = new SellingRequestModel(sellerEmail,
                Integer.parseInt(orderNumberString), sellerName);
        return boundary.accept(requestModel);
    }
}
