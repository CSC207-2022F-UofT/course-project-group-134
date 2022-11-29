package entities;

/**
 * This class represents the subclass of User that is a Buyer. A buyer is a (RAVENOUS) User who seeks sustenance by
 * means of purchasing or otherwise receiving food from Sellers on our service.
 * <p>
 * The Buyer class has no additional parameters other than the ones it inherits from the User class.
 *
 * @author Vivian Liu
 * @author Tejas Balaji
 */
public class Buyer extends User{

    /**
     * Constructor for Buyer. This functions identically to the constructor for User and, in fact, uses User's
     * constructor.
     *
     * @param name The Buyer's username
     * @param password The Buyer's password
     * @param email The Buyer's email
     */
    public Buyer(String name, String password, String email){
        super(name, password, email);
        super.userType = UserType.BUYER;
    }

    /**
     * This buyer places an order.
     * @param order The order to be placed.
     *
     *              TODO: implement this function
     */
    public void placeOrder(Order order){
        // TODO place in database
    }

    @Override
    public String toString() {
        return "Buyer: " + this.getUsername();
    }
}
