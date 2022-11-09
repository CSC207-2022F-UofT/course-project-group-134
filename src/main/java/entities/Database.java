package entities;

import java.util.ArrayList;

public class Database {

    private ArrayList<User> users;
    private ArrayList<Seller> sellers;
    private ArrayList<Buyer> buyers;
    private ArrayList<DiningHall> diningHalls;
    private ArrayList<Order> orders;
    private User currentUser;

    public Database() {
        this.users = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.buyers = new ArrayList<>();
        this.diningHalls = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public ArrayList<DiningHall> getDiningHalls() {
        return diningHalls;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
