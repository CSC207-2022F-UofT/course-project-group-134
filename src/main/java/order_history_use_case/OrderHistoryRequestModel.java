package order_history_use_case;

public class OrderHistoryRequestModel {

    private final String username;
    private final String email;

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
