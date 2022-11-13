package user_access_use_case;

import java.util.ArrayList;

public class UserDsRequestModel {
    private String username;
    private String password;
    private String email;

    private String userType;

    private ArrayList<String> allowedDiningHalls;    // List of names of dining halls where the meal plan can be used

    private double mealPlanBalance;

    public UserDsRequestModel(String username, String password, String email, String userType, double
                              mealPlanBalance, ArrayList<String> associatedDiningHalls ){

        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.mealPlanBalance = mealPlanBalance;
        this.allowedDiningHalls = associatedDiningHalls;
    }

    public String getUsername() {return this.username;}
    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUserType() {
        return userType;
    }

    public double getMealPlanBalance() {
        return mealPlanBalance;
    }

    public ArrayList<String> getAllowedDiningHalls() {
        return allowedDiningHalls;
    }
}
