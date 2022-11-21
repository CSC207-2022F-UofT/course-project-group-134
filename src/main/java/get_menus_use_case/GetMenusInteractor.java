package get_menus_use_case;

import entities.Menu;
import entities.ResidenceType;

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
                return presenter.prepareSuccessView(responseModel);
            }
        }
        return null;
    }
}
