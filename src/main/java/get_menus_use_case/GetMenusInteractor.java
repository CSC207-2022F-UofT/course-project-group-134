package get_menus_use_case;

import entities.FoodItem;
import entities.Menu;
import entities.ResidenceType;
import entities.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetMenusInteractor implements GetMenusInputBoundary {
    private final GetMenusOutputBoundary presenter;
    private final MenuGatewayInterface menuGatewayInterface;

    private GetMenusResponseModel resMod;

    /**
     *
     * @param presenter The presenter for this use case; it is an Output Boundary here to invert the dependency
     * @param menuGateway The Gateway class for this usecase - used to get data from csv files
     */

    public GetMenusInteractor(GetMenusOutputBoundary presenter, MenuGatewayInterface menuGateway) {
        this.presenter = presenter;
        this.menuGatewayInterface = menuGateway;
    }

    /**
     * @param residenceName The name of the residence from which to get the menu (each residence has a menu)
     * This method sets up the interactor by appropriately setting up the response model based on the residence given
     */
    public void setUpInteractor(String residenceName) throws Exception {
        ResidenceType[] arr = ResidenceType.values();
        for (ResidenceType residenceType : arr) {
            if (residenceType.name().equals(residenceName)) {
                List<GetMenusGatewayResponseModel> responseModels = this.menuGatewayInterface.createMenu(residenceType);
                List<FoodItem> foodItems = new ArrayList<>();
                for (GetMenusGatewayResponseModel model: responseModels){
                    FoodItem foodItem = new FoodItem(model.getDescription(), model.getAllergens(), model.getIngredients(), model.getCalories(), model.getPrice());
                    foodItems.add(foodItem);
                }
                Menu menu = new Menu(foodItems);

                HashMap<String, List<Review>> foodReviews = new HashMap<>();

                for (String foodItem : menu.getFoodItemNames()) {
                    foodReviews.put(foodItem, menuGatewayInterface.getFoodReviews(foodItem, residenceType));
                }

                this.resMod = new GetMenusResponseModel(
                        menu.getFoodItemNames(),
                        menu.getFoodItemPrices(),
                        menu.getFoodItemAllergens(),
                        menu.getFoodItemIngredients(),
                        menu.getFoodItemCalories(),
                        menu.getFoodItemPopularities(),
                        menu.getFoodItemStarAverages(),
                        foodReviews
                );

                break;
            }
        }
    }

    /**
     * @return Return what the presenter returns when getFoodDetails is called on it
     */
    @Override
    public List<String[]> getFoodDetails() {
        return presenter.getFoodDetails(this.resMod);

    }

    /**
     * @return Return what the presenter returns when getFoodReviews is called on it
     */
    @Override
    public HashMap<String, List<String[]>> getFoodReviews(){

        return presenter.getFoodReviews(this.resMod);
    }
}
