package selling_use_case;

import user_access_use_case.SignUpInputBoundary;

import java.io.IOException;

public class SellingController {
    SellingInputBoundary boundary;

    public SellingController(SellingInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public SellingResponseModel accept(String sellerEmail, String orderNumberString) throws IOException {
        SellingRequestModel requestModel = new SellingRequestModel(sellerEmail,
                Integer.parseInt(orderNumberString));
        return boundary.accept(requestModel);
    }
}
