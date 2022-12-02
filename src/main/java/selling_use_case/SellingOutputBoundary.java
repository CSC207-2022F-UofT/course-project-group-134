package selling_use_case;

/**
 * SellingOutputBoundary which purpose is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface SellingOutputBoundary {
    void prepareFailView(String error);
    SellingResponseModel prepareSuccessView(SellingResponseModel info);
}
