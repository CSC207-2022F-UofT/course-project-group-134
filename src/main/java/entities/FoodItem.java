package entities;

public class FoodItem {
    private String description;
    private String[] allergens;
    private String[] ingredients;
    private int calories;
    private double price;
    private int popularity;

    public FoodItem(String description, String[] allergens, String[] ingredients, int calories, double price){
        this.description = description;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.calories = calories;
        this.price = price;
        this.popularity = 0;
    }

    public String getDescription(){
        return this.description;
    }

    public String[] getAllergens(){
        return this.allergens;
    }

    public String[] getIngredients(){
        return this.ingredients;
    }

    public int getCalories(){
        return this.calories;
    }

    public double gerPrice(){
        return this.price;
    }

    public int getPopularity(){
        return popularity;
    }

}
