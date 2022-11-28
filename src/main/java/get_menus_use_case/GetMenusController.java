package get_menus_use_case;

import java.util.ArrayList;
import java.util.HashMap;

public class GetMenusController {

    private final GetMenusInputBoundary inputBoundary;

    public GetMenusController(GetMenusInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public ArrayList<String[]> getFoodDetails() throws Exception {
        return this.inputBoundary.getFoodDetails();
    }

    public HashMap<String, ArrayList<String[]>> getFoodReviews(){
        return this.inputBoundary.getFoodReviews();
    }

    public void setUpInteractor(String residenceName) throws Exception {
        this.inputBoundary.setUpInteractor(residenceName);
    }

}
