package user_access_use_case;

import java.util.ArrayList;

public class UserDsRequestModel {
    private String username;
    private String password;
    private String email;
    private String userType;
    private String residence;
    private double mealPlanBalance;

    public UserDsRequestModel(String username, String password, String email, String userType, double
                              mealPlanBalance, String residence){
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.mealPlanBalance = mealPlanBalance;
        this.residence = residence;
    }

    public String getUsername() {
        return this.username;
    }

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

    public String getResidence() {
        return residence;
    }
}
