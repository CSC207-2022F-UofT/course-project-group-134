package get_menus_use_case;

public interface GetMenusInputBoundary {
    GetMenusResponseModel getFoodItemNames(String residenceName) throws Exception;
}
