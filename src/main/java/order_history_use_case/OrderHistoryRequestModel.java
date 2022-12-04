package order_history_use_case;

public class OrderHistoryRequestModel {


    private String username;
    private String email;

    /**
     *
     * @param username  Identifies the user whose order history is to be displayed
     * @param email Belongs to the user whose order history is to be displayed
     */

    public OrderHistoryRequestModel(String username,  String  email){
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
