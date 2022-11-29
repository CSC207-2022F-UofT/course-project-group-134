package get_menus_use_case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetMenusController {

    private final GetMenusInputBoundary inputBoundary;

    /**
     *
     * @param inputBoundary The Use Case interactor for the Get Menus Use case (inputted here as an input boundary
     *                      for inverting the dependency)
     */
    public GetMenusController(GetMenusInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     *
     * @return Return what the input boundary returns when getFoodDetails is called on it
     */
    public ArrayList<String[]> getFoodDetails() {
        return this.inputBoundary.getFoodDetails();
    }

    /**
     *
     * @return Return what the input boundary returns when getFoodReviews is called on it
     */
    public HashMap<String, ArrayList<String[]>> getFoodReviews(){

        return this.inputBoundary.getFoodReviews();
    }

    /**
     *
     * @param residenceName The residence name from which to obtain the menu
     * This method is used to set up the use case interactor (see use case interactor for details)
     */
    public void setUpInteractor(String residenceName) throws Exception {
        this.inputBoundary.setUpInteractor(residenceName);
    }

}
