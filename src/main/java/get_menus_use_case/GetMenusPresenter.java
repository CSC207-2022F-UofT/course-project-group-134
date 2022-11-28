package get_menus_use_case;

import entities.Review;

import java.util.ArrayList;
import java.util.HashMap;

public class GetMenusPresenter implements GetMenusOutputBoundary{

    /*
    This takes in a GetMenusReponseModel and gives out a list of String[] where each String[] contains the following
    details of a food item

        0. Name
        1. Price
        2. Allergens
        3. Ingredients
        4. Calories
        5. Popularities
        6. Star average
     */
    public ArrayList<String[]> getFoodDetails(GetMenusResponseModel responseModel){
        ArrayList<String[]> foodDetailsArray = new ArrayList<>();
        for(int i = 0; i < responseModel.getFoodItemNames().size(); i++){
            String[] foodDetails = {responseModel.getFoodItemNames().get(i),
            String.valueOf(responseModel.getFoodItemPrices().get(i)),
            String.join(",", responseModel.getFoodItemAllergens().get(i)),
            String.join(",", responseModel.getFoodItemIngredients().get(i)),
            String.valueOf(responseModel.getFoodItemCalories().get(i)),
            String.valueOf(responseModel.getFoodItemPopularities().get(i)),
            String.valueOf(responseModel.getFoodItemStarAverages().get(i))
            };

            foodDetailsArray.add(foodDetails);
        }

        return foodDetailsArray;
    }


    /*
    Takes in a responseModel and returns a hashmap such that
    1. its keys are the food items
    2. each key is mapped to an arrayList of String[]
    3. each string[] contains the review string, rating, reviewer's username (in that order)
     */
    public HashMap<String, ArrayList<String[]>> getFoodReviews(GetMenusResponseModel responseModel){
        HashMap<String, ArrayList<String[]>> foodItemsAndReviews = new HashMap<>();

        for(String foodName: responseModel.getFoodItemReviews().keySet()){
            ArrayList<String[]> reviewList = new ArrayList<>();

            for(Review review: responseModel.getFoodItemReviews().get(foodName)){
                String[] reviewStuff = {review.getReviewString(), String.valueOf(review.getRating()),
                review.getBuyer()};
                reviewList.add(reviewStuff);
            }

            foodItemsAndReviews.put(foodName, reviewList);
        }

        return foodItemsAndReviews;
    }

}
