package entities;

public class UserFactory {

    private BuyerFactory buyerFactory;
    private SellerFactory sellerFactory;

    public Buyer createBuyer(UserType type, String name, String password, String email){

        return buyerFactory.create(name, password, email);
        }

    public Seller createSeller(UserType type, String name, String password, MealPlan mealPlan, String email){
        return sellerFactory.create(name, password, mealPlan, email);
        }
    }

