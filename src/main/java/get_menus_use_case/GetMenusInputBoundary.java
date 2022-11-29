package get_menus_use_case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GetMenusInputBoundary {

    List<String[]> getFoodDetails();
    HashMap<String, List<String[]>> getFoodReviews();

    void setUpInteractor(String residenceName) throws Exception;
}
