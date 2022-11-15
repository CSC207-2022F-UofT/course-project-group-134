package selling_use_case;

public class SellingGateway implements SellingDsGateway{

    public void addOrder(SellingDsRequestModel data) {

    }

    public void updateOrder(int orderNumber, String orderStatus, String sellerUsername) {

    }

    public boolean orderExistsById(int orderNumber) {
        return false;
    }

    public String getOrderStatus(int orderNumber) {
        return null;
    }

    public void save(SellingDsRequestModel sellingDsModel) {

    }
}
