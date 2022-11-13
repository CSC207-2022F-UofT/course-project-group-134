package entities;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuFactory {

    public Menu createMenu(ResidenceType type) throws Exception
    {
        String name = type.name();
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        // Below scanner used for finding the number of lines in the csv file
        Scanner scTemp = new Scanner(new File("src/main/Dining_Halls/"+ name +".csv"));

        scTemp.useDelimiter("\n");
        int counter = 0;
        while (scTemp.hasNext()){
            System.out.println(scTemp.next());
            counter ++;
        }
        System.out.println(counter);

        // Below scanner used for the actual creation of the food items and the menu
        Scanner sc = new Scanner(new File("src/main/Dining_Halls/"+ name +".csv"));
        sc.useDelimiter(",|\n");

        for (int i = 0; i < counter; i++) {
            String description = sc.next();
            String[] allergens = sc.next().split(";");
            String[] ingredients = sc.next().split(";");
            int calories = Integer.parseInt(sc.next());
            double price = Double.parseDouble(sc.next());
            FoodItem foodItem = new FoodItem(description, allergens, ingredients, calories, price);
            foodItems.add(foodItem);

        }
        sc.close();  //closes the scanner
        return new Menu(foodItems);
    }

    //NOTE: the below main method is a sample to test the running of MenuFactory
    /*public static void main(String[] args) throws Exception{
        MenuFactory menuFactory = new MenuFactory();
        menuFactory.createMenu(DiningHallTypes.NEW_COLLEGE);

    }*/




}
