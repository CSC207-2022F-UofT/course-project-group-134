package selling_use_case;

public class SellingPresenter implements SellingOutputBoundary {
    @Override
    public void prepareFailView(String error) {
        throw new SellingFailed(error);
    }

    @Override
    public void prepareSuccessView() {

    }
}
