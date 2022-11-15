package selling_use_case;

public interface SellingDsGateway {
    public void addOrder(SellingDsRequestModel data);
    public void updateOrder(int orderNumber, String orderStatus, String sellerUsername);
    public boolean orderExistsById(int orderNumber);
    public String getOrderStatus(int orderNumber);

    public void save(SellingDsRequestModel sellingDsModel);
}
