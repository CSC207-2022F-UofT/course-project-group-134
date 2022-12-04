package get_menus_use_case;

import entities.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetMenusPresenter implements GetMenusOutputBoundary{


    /**
     *
     * @param responseModel Contains all the information of all the food items from a residence
     * @return A list of String[] where each String[] contains the details of all foods in the following order:
            0. Name
            1. Price
            2. Allergens
            3. Ingredients
            4. Calories
            5. Popularity
            6. Star average
     */
    public List<String[]> getFoodDetails(GetMenusResponseModel responseModel){

        List<String[]> foodDetailsArray = new ArrayList<>();

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


    /**
     *
     * @param responseModel Contains all the information of all the food items from a residence
     * @return A hashmap whose keys are food items. Each key is mapped to an arrayList of String[] and each
     *  String[] contains the review string, rating, reviewer's username (in that order)
     */
    public HashMap<String, List<String[]>> getFoodReviews(GetMenusResponseModel responseModel){
        HashMap<String, List<String[]>> foodItemsAndReviews = new HashMap<>();

        for(String foodName: responseModel.getFoodItemReviews().keySet()){
            List<String[]> reviewList = new ArrayList<>();

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
