package screens;

import java.util.ArrayList;
import java.util.HashMap;

public interface OrderViewModel {
    public void showMenus(ArrayList<String[]> foodDetails, HashMap<String, ArrayList<String[]>> foodReviews);
}
