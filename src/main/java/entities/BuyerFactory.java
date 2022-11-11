package entities;

public class BuyerFactory {
    public Buyer create(String name, String password, String email) {
        return new Buyer(name, password, email);
    }
}
