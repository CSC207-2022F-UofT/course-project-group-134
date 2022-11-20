package user_access_use_case;

public class SignUpDsRequestModel {
    private final String username;
    private final String password;
    private final String email;
    private final String userType;
    private final String residence;
    private double mealPlanBalance;

    public SignUpDsRequestModel(String username, String password, String email, String userType, double
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

    public void setMealPlanBalance(double newPrice) {
        this.mealPlanBalance = newPrice;
    }
}
