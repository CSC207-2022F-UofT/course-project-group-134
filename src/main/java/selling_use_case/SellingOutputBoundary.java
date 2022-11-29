package selling_use_case;

public interface SellingOutputBoundary {
    void prepareFailView(String error);
    SellingResponseModel prepareSuccessView(SellingResponseModel info);
}
