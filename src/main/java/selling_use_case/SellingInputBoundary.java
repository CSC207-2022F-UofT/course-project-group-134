package selling_use_case;

import java.io.IOException;

/**
 * SellingInputBoundary which purpose is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface SellingInputBoundary {
    SellingResponseModel accept(SellingRequestModel requestModel) throws IOException;
}
