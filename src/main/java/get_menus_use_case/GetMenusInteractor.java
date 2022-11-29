package get_menus_use_case;

import entities.Menu;
import entities.ResidenceType;
import entities.Review;

import java.util.HashMap;
import java.util.List;

public class GetMenusInteractor implements GetMenusInputBoundary {
    private final GetMenusOutputBoundary presenter;
    private final MenuGatewayInterface menuGatewayInterface;

    private GetMenusResponseModel resMod;

    public GetMenusInteractor(GetMenusOutputBoundary presenter, MenuGatewayInterface menuGateway) throws Exception {
        this.presenter = presenter;
        this.menuGatewayInterface = menuGateway;


    }

    public void setUpInteractor(String residenceName) throws Exception {
        ResidenceType[] arr = ResidenceType.values();
        for (ResidenceType residenceType : arr) {
            if (residenceType.name().equals(residenceName)) {
                Menu menu = this.menuGatewayInterface.createMenu(residenceType);

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

    @Override
    public List<String[]> getFoodDetails() {
        return presenter.getFoodDetails(this.resMod);

    }

    @Override
    public HashMap<String, List<String[]>> getFoodReviews(){

        return presenter.getFoodReviews(this.resMod);
    }
}
