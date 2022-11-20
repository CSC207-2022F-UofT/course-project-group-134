package get_menus_use_case;

import entities.Menu;
import entities.MenuFactory;
import entities.ResidenceType;

public class GetMenusInteractor implements GetMenusInputBoundary {
    private final GetMenusOutputBoundary presenter;
    private final MenuFactory menuFactory;

    public GetMenusInteractor(GetMenusOutputBoundary presenter, MenuFactory menuFactory){
        this.presenter = presenter;
        this.menuFactory = menuFactory;
    }
    @Override
    public GetMenusResponseModel getFoodItemNames(String residenceName) throws Exception {
        System.out.println("In Interactor");

        ResidenceType[] arr = ResidenceType.values();
        for (ResidenceType residenceType : arr) {
            if (residenceType.name().equals(residenceName)) {
                Menu menu = this.menuFactory.createMenu(residenceType);
                GetMenusResponseModel responseModel = new GetMenusResponseModel(
                        menu.getFoodItemNames(),
                        menu.getFoodItemPrices(),
                        menu.getFoodItemAllergens(),
                        menu.getFoodItemIngredients(),
                        menu.getFoodItemCalories(),
                        menu.getFoodItemPopularities(),
                        menu.getFoodItemStarAverages(),
                        menu.getFoodItemReviews()
                );
                System.out.println("About to go to presenter");
                return presenter.prepareSuccessView(responseModel);
            }
        }
        return null;
    }
}
