package get_menus_use_case;
import entities.FoodItem;
import entities.Menu;
import entities.ResidenceType;
import entities.Review;
import order_history_use_case.OrderHistoryResponseModel;
import review_use_case.ReviewGateway;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class MenuGateway implements  MenuGatewayInterface{

    private List<FoodItem> foodItems;
    private ResidenceType type;
    public Menu createMenu(ResidenceType type) throws Exception {
        String name = type.name();
        this.foodItems = new ArrayList<>();
        this.type = type;

        // Below scanner used for finding the number of lines in the csv file
        Scanner scTemp = new Scanner(new File("src/main/java/data_storage/Dining_Halls/"+ name +".csv"));

        scTemp.useDelimiter("\n");
        int counter = 0;
        while (scTemp.hasNext()){
            System.out.println(scTemp.next());
            counter ++;
        }
        System.out.println(counter);

        // Below scanner used for the actual creation of the food items and the menu
        Scanner sc = new Scanner(new File("src/main/java/data_storage/Dining_Halls/"+ name +".csv"));
        sc.useDelimiter("[,\n]");

        for (int i = 0; i < counter; i++) {
            String description = sc.next();
            String[] allergens = sc.next().split(";");
            String[] ingredients = sc.next().split(";");
            int calories = Integer.parseInt(sc.next());
            double price = Double.parseDouble(sc.next());
            FoodItem foodItem = new FoodItem(description, allergens, ingredients, calories, price);
            this.foodItems.add(foodItem);
        }

        return new Menu(foodItems);
    }

    // Takes in the name of a food item and returns all the reviews associated with the item in the given dining hall
    public List<Review> getFoodReviews(String foodItem, ResidenceType residenceType) throws IOException {
        ReviewGateway gateway = new ReviewGateway("src/main/java/data_storage/reviews.csv");
        return gateway.getReviewsFromName(foodItem, residenceType);
    }

    //NOTE: the below main method is a sample to test the running of MenuFactory
    /*public static void main(String[] args) throws Exception{
        MenuFactory menuFactory = new MenuFactory();
        menuFactory.createMenu(DiningHallTypes.NEW_COLLEGE);

    }*/




}
