package selling_use_case;

import user_access_use_case.SignUpResponseModel;

public class SellingPresenter implements SellingOutputBoundary {

    public void prepareFailView(String error) {
        throw new SellingFailed(error);
    }

    public SellingResponseModel prepareSuccessView(SellingResponseModel info) {
        return info;
    }
}
