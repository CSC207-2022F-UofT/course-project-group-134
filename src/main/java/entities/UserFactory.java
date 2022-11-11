package entities;

public class UserFactory {

    private BuyerFactory buyerFactory;
    private SellerFactory sellerFactory;

    public Buyer createBuyer(UserType type, String name, String password){

        return buyerFactory.create(name, password);
        }

    public Seller createSeller(UserType type, String name, String password, MealPlan mealPlan){
        return sellerFactory.create(name, password, mealPlan);
        }
    }

