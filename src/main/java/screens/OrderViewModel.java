package screens;

import java.util.HashMap;
import java.util.List;

public interface OrderViewModel {
    void showMenus(List<String[]> foodDetails, HashMap<String, List<String[]>> foodReviews);
}
