package get_menus_use_case;

public class GetMenusController {

    private final GetMenusInputBoundary inputBoundary;

    public GetMenusController(GetMenusInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public GetMenusResponseModel getFoodItemNames(String residenceName) throws Exception {
        return this.inputBoundary.getFoodItemNames(residenceName);
    }

    // TODO: Create method to return reviews for a given food item + residenceName
}
