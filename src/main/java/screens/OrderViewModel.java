package screens;

import java.util.List;
import java.util.Map;

public interface OrderViewModel {
    void showMenus(List<String[]> foodDetails, Map<String, List<String[]>> foodReviews);
}
