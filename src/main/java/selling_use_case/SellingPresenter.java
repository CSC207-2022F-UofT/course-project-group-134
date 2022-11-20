package selling_use_case;

public class SellingPresenter implements SellingOutputBoundary {

    public void prepareFailView(String error) {
        throw new SellingFailed(error);
    }

    public SellingResponseModel prepareSuccessView(SellingResponseModel info) {
        return info;
    }
}
