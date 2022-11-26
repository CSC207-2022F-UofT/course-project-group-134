package get_menus_use_case;

import entities.Review;

import java.util.ArrayList;
import java.util.HashMap;

public class GetMenusPresenter implements GetMenusOutputBoundary{

    public HashMap<String, ArrayList<String[]>> prepareSuccessView(GetMenusResponseModel responseModel){
        HashMap<String, ArrayList<String[]>> foodItemsAndReviews = new HashMap<>();

        for(String foodName: responseModel.getFoodItemReviews().keySet()){
            ArrayList<String[]> reviewList = new ArrayList<>();

            for(Review review: responseModel.getFoodItemReviews().get(foodName)){
                String[] reviewStuff = {review.getReviewString(), String.valueOf(review.getRating()),
                review.getBuyer().getUsername()};
                reviewList.add(reviewStuff);
            }

            foodItemsAndReviews.put(foodName, reviewList);
        }

        return foodItemsAndReviews;
    }

    /*
    Takes in a responseModel and returns a hashmap such that
    1. its keys are the food items
    2. each key is mapped to an arrayList of String[]
    3. each string[] contains the review string, rating, reviewer's username (in that order)
     */
}
