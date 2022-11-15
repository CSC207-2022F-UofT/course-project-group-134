package selling_use_case;

import user_access_use_case.SignUpRequestModel;
import user_access_use_case.SignUpResponseModel;

import java.io.IOException;

public interface SellingInputBoundary {
    SellingResponseModel accept(SellingRequestModel requestModel) throws IOException;
}
