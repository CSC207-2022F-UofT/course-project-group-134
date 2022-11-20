package selling_use_case;

import java.io.IOException;

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
