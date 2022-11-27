package get_menus_use_case;

import java.util.ArrayList;
import java.util.HashMap;

public interface GetMenusOutputBoundary{
    ArrayList<String[]> getFoodDetails(GetMenusResponseModel responseModel);
    HashMap<String, ArrayList<String[]>> getFoodReviews(GetMenusResponseModel responseModel);
}
