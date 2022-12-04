package get_menus_use_case;

import java.util.List;
import java.util.Map;

public interface GetMenusInputBoundary {

    List<String[]> getFoodDetails();
    Map<String, List<String[]>> getFoodReviews();

    void setUpInteractor(String residenceName) throws Exception;
}
