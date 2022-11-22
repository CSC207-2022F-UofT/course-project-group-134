package get_menus_use_case;

public class GetMenusPresenter implements GetMenusOutputBoundary{

    @Override
    public GetMenusResponseModel prepareSuccessView(GetMenusResponseModel responseModel) {
        System.out.println(responseModel.getFoodItemNames().toString());
        return responseModel;
        /*return orderViewModel.showMenus(
                responseModel.getFoodItemNames(),
                responseModel.getFoodItemPrices(),
                responseModel.getFoodItemAllergens(),
                responseModel.getFoodItemIngredients(),
                responseModel.getFoodItemCalories(),
                responseModel.getFoodItemPopularities(),
                responseModel.getFoodItemStarAverages(),
                responseModel.getFoodItemReviews()
        );*/
    }
}
