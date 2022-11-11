package entities;

public class Buyer extends User{
    public Buyer(String name, String password, String email){
        super(name, password, email);
    }

    public void placeOrder(Order order){
        // TODO place in database
    }
}
