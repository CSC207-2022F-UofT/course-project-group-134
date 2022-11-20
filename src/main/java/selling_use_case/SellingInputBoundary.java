package selling_use_case;

import java.io.IOException;

public interface SellingInputBoundary {
    void accept(SellingRequestModel requestModel) throws IOException;
}
