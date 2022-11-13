package user_access_use_case;

import entities.MealPlan;
import entities.UserType;

public class UserRequestModel {
    private String username;
    private String email;
    private String password;
    private UserType userType;
    private MealPlan mealPlan;

    public UserRequestModel (String name, String email, String password, UserType userType, MealPlan mealPlan){
        this.username = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.mealPlan = mealPlan;
    }

    public UserRequestModel (String name, String email, String password, UserType userType){
        this.username = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.mealPlan = null;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }
}
