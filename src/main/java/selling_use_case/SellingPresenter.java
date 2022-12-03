package selling_use_case;

/**
 * The SellingPresenter either returns a success view if the selling information
 * is valid, or a fail view if the information provided is invalid.
 */
public class SellingPresenter implements SellingOutputBoundary {

    public void prepareFailView(String error) {
        throw new SellingFailed(error);
    }

    public SellingResponseModel prepareSuccessView(SellingResponseModel info) {
        return info;
    }
}
