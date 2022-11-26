package get_menus_use_case;

import entities.FoodItem;
import entities.Menu;
import entities.ResidenceType;
import entities.Review;

import java.util.ArrayList;
import java.util.HashMap;

public class GetMenusInteractor implements GetMenusInputBoundary {
    private final GetMenusOutputBoundary presenter;
    private final MenuGatewayInterface menuGatewayInterface;

    public GetMenusInteractor(GetMenusOutputBoundary presenter, MenuGatewayInterface menuGateway){
        this.presenter = presenter;
        this.menuGatewayInterface = menuGateway;

    }
    @Override
    public GetMenusResponseModel getFoodItemNames(String residenceName) throws Exception {

        ResidenceType[] arr = ResidenceType.values();
        for (ResidenceType residenceType : arr) {
            if (residenceType.name().equals(residenceName)) {
                Menu menu = this.menuGatewayInterface.createMenu(residenceType);

                HashMap<String, ArrayList<Review>> foodReviews = new HashMap<>();

                for(String foodItem: menu.getFoodItemNames()){
                    foodReviews.put(foodItem, menuGatewayInterface.getFoodReviews(foodItem, residenceType));
                }

                GetMenusResponseModel responseModel = new GetMenusResponseModel(
                        menu.getFoodItemNames(),
                        menu.getFoodItemPrices(),
                        menu.getFoodItemAllergens(),
                        menu.getFoodItemIngredients(),
                        menu.getFoodItemCalories(),
                        menu.getFoodItemPopularities(),
                        menu.getFoodItemStarAverages(),
                        foodReviews
                );
                return presenter.prepareSuccessView(responseModel);
            }
        }
        return null;
    }
}
