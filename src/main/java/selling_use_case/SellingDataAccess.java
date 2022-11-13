package selling_use_case;

public class SellingDataAccess implements SellingDataAccessInterface{
    @Override
    public void addOrder(SellingDsRequestModel data) {

    }

    @Override
    public void updateOrder(int orderNumber, String orderStatus, String sellerUsername) {

    }

    @Override
    public boolean orderExistsById(int orderNumber) {
        return true;
    }

    @Override
    public String getOrderStatus(int orderNumber) {
        return null;
    }
}
