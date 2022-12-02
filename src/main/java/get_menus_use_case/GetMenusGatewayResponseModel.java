package get_menus_use_case;

public class GetMenusGatewayResponseModel {

    private String description;
    private String[] allergens;
    private String[] ingredients;
    private int calories;
    private double price;


    public GetMenusGatewayResponseModel(String description, String[] allergens, String[] ingredients, int calories, double price) {
        this.description = description;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.calories = calories;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAllergens() {
        return allergens;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }
}
