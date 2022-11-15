package get_menus_use_case;

import entities.ResidenceType;

public class GetMenusController {

    private GetMenusInputBoundary inputBoundary;

    public GetMenusController(GetMenusInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public GetMenusResponseModel getFoodItemNames(String residenceName) throws Exception {
        return this.inputBoundary.getFoodItemNames(residenceName);
    }
}
