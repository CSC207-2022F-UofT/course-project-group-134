package get_menus_use_case;

import java.util.List;
import java.util.Map;

public interface GetMenusOutputBoundary{
    List<String[]> getFoodDetails(GetMenusResponseModel responseModel);
    Map<String, List<String[]>> getFoodReviews(GetMenusResponseModel responseModel);
}
