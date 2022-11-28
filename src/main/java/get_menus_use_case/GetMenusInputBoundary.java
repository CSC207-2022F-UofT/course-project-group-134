package get_menus_use_case;

import java.util.ArrayList;
import java.util.HashMap;

public interface GetMenusInputBoundary {

    ArrayList<String[]> getFoodDetails();
    HashMap<String, ArrayList<String[]>> getFoodReviews();
}
