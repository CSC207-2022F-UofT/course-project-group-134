package selling_use_case;

import user_access_use_case.SignUpInputBoundary;

public class SellingController {
    SellingInputBoundary boundary;

    public SellingController(SellingInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }
}
