package get_menus_use_case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GetMenusOutputBoundary{
    List<String[]> getFoodDetails(GetMenusResponseModel responseModel);
    HashMap<String, List<String[]>> getFoodReviews(GetMenusResponseModel responseModel);
}
