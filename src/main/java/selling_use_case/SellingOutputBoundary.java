package selling_use_case;

public interface SellingOutputBoundary {
    public void prepareFailView(String error);
    public SellingResponseModel prepareSuccessView(SellingResponseModel info);
}
