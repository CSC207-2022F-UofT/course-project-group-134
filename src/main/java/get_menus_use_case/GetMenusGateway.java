package get_menus_use_case;
import entities.ResidenceType;
import entities.Review;
import review_use_case.ReviewGateway;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetMenusGateway implements  MenuGatewayInterface{

    private List<GetMenusGatewayResponseModel> responseModels;
    private ResidenceType type;

    /**
     *
     * @param type the residence whose menu we wish to fetch
     * @return return an ArrayList of response models which the interactor can use to create the menu object
     */
    public List<GetMenusGatewayResponseModel> createMenu(ResidenceType type) throws Exception
    {

        String name = type.name();
        this.responseModels = new ArrayList<>();
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
            GetMenusGatewayResponseModel responseModel = new GetMenusGatewayResponseModel(description, allergens, ingredients, calories, price);
            this.responseModels.add(responseModel);
        }

        return this.responseModels;
    }

    // Takes in the name of a food item and returns all the reviews associated with the item in the given dining hall
    public List<Review> getFoodReviews(String foodItem, ResidenceType residenceType) throws IOException {
        ReviewGateway gateway = new ReviewGateway("src/main/java/data_storage/reviews.csv");
        return gateway.getReviewsFromName(foodItem, residenceType);
    }
}
