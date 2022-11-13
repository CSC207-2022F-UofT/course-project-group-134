package selling_use_case;

public interface SellingDataAccessInterface {
    public void addOrder(SellingDsRequestModel data);
    public void updateOrder(int orderNumber, String orderStatus, String sellerUsername);
    public boolean orderExistsById(int orderNumber);
    public String getOrderStatus(int orderNumber);
}
