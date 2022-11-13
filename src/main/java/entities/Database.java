package entities;

import java.util.ArrayList;

public class Database {

    private ArrayList<User> users;
    private ArrayList<Seller> sellers;
    private ArrayList<Buyer> buyers;
    private ArrayList<Residence> diningHalls;
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

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public ArrayList<Residence> getDiningHalls() {
        return diningHalls;
    }

    public void addDiningHall(Residence diningHall) {
        diningHalls.add(diningHall);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
