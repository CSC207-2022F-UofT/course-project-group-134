package entities;

public class BuyerFactory {
    public Buyer create(String name, String password) {
        return new Buyer(name, password);
    }
}
