package get_menus_use_case;

import entities.FoodItem;
import entities.Menu;
import entities.ResidenceType;
import entities.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GetMenusInteractor implements GetMenusInputBoundary {
    private final GetMenusOutputBoundary presenter;
    private final MenuGatewayInterface menuGatewayInterface;

    private GetMenusResponseModel resMod;

    public GetMenusInteractor(GetMenusOutputBoundary presenter, MenuGatewayInterface menuGateway, String residenceName) throws Exception {
        this.presenter = presenter;
        this.menuGatewayInterface = menuGateway;

        ResidenceType[] arr = ResidenceType.values();
        for (ResidenceType residenceType : arr) {
            if (residenceType.name().equals(residenceName)) {
                Menu menu = this.menuGatewayInterface.createMenu(residenceType);

                HashMap<String, ArrayList<Review>> foodReviews = new HashMap<>();

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

    @Override
    public ArrayList<String[]> getFoodDetails() {
        return presenter.getFoodDetails(this.resMod);

    }

    @Override
    public HashMap<String, ArrayList<String[]>> getFoodReviews(){

        return presenter.getFoodReviews(this.resMod);
    }
}
